package com.revature.crs.Course;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.interfaces.Crudable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Crudable<Course> {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    // Data Access Object (DAO)
    @Override
    public boolean update(Course updatedCourse) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            CallableStatement callableStatement = conn.prepareCall("call update_course(?,?,?,?,?,?)");
            callableStatement.setInt(1, updatedCourse.getCourseId());
            callableStatement.setString(2, updatedCourse.getCourseCode());
            callableStatement.setString(3, updatedCourse.getCourseTitle());
            callableStatement.setShort(4, updatedCourse.getCapacity());
            callableStatement.setShort(5, updatedCourse.getEnrolled());
            callableStatement.setInt(6, updatedCourse.getProfessor());

            log.info("SQL: {}", callableStatement);
            callableStatement.execute();
            callableStatement.close();

            return true;
            //TODO: uhhh maybe do something about this?
//            if (preparedStatement.executeUpdate() == 0) throw new RuntimeException("Course not found.");
//            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int courseId) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete from courses where course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, courseId);

            log.info("SQL: {}", preparedStatement);

            if (preparedStatement.executeUpdate() == 0) throw new DataNotFoundException("Course not found.");
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Course> findAll() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Course> courses = new ArrayList<>();
            String sql = "select * from courses";

            log.info("SQL: {}", sql);
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) courses.add(generateCourseFromResultSet(resultSet));
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course create(Course newCourse) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "insert into courses values (default,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newCourse.getCourseCode());
            preparedStatement.setString(2, newCourse.getCourseTitle());
            preparedStatement.setShort(3, newCourse.getCapacity());
            preparedStatement.setShort(4, newCourse.getEnrolled());
            preparedStatement.setInt(5, newCourse.getProfessor());

            log.info("SQL: {}", preparedStatement);
            if (preparedStatement.executeUpdate() == 0) {
                log.warn("course: {} not added", newCourse);
                throw new RuntimeException("Course not added");
            }
            log.info("course: {} added", newCourse);
            return newCourse;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findById(int id) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "select * from courses where course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            log.info("SQL: {}", preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                log.warn("Information not found for courseId: {}", id);
                throw new DataNotFoundException("No course with id:" + id + " exists in database.");
            }
            Course foundCourse = generateCourseFromResultSet(resultSet);
            log.info("returning found course: {}", foundCourse);
            return foundCourse;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> findAvailable() {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Course> courses = new ArrayList<>();
            String sql = "select * from courses where enrolled < capacity";

            log.info("SQL: {}", sql);
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            while (resultSet.next()) courses.add(generateCourseFromResultSet(resultSet));
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Course> getEnrolled(int curUser) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Course> courses = new ArrayList<>();
            String sql = "select c.* from courses c " +
                    "inner join registrations r on c.course_id = r.course " +
                    "where r.student = ? ;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, curUser);

            log.info("SQL: {}", preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) courses.add(generateCourseFromResultSet(resultSet));
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Course generateCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("course_id"));
        course.setCourseCode(resultSet.getString("course_code"));
        course.setCourseTitle(resultSet.getString("course_title"));
        course.setCapacity(resultSet.getShort("capacity"));
        course.setEnrolled(resultSet.getShort("enrolled"));
        course.setProfessor(resultSet.getInt("professor"));
        return course;
    }
}
