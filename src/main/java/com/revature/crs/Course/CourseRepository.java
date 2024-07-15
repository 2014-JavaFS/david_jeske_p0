package com.revature.crs.Course;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.revature.crs.util.CourseRegistrationFrontController.logger;

public class CourseRepository implements Crudable<Course> {
    // Data Access Object (DAO)
    @Override
    public boolean update(Course updatedCourse) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "update courses " +
                    "set { course_id = ?, course_code = ?, course_title = ?," +
                    " capacity = ?, enrolled = ?, professor = ? } where course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, updatedCourse.getCourseId());
            preparedStatement.setString(2, updatedCourse.getCourseCode());
            preparedStatement.setString(3, updatedCourse.getCourseTitle());
            preparedStatement.setShort(4, updatedCourse.getCapacity());
            preparedStatement.setShort(5, updatedCourse.getEnrolled());
            preparedStatement.setInt(6, updatedCourse.getProfessor());
            preparedStatement.setInt(7, updatedCourse.getCourseId());

            logger.info(preparedStatement.toString());
            if (preparedStatement.executeUpdate(sql) == 0) throw new RuntimeException("Course not found.");
            else return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int courseId) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            String sql = "delete * from courses where course_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, courseId);

            logger.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) throw new DataNotFoundException("Course not found.");
            return true;
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

            logger.info(sql);
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

            logger.info(preparedStatement.toString());
            if (preparedStatement.executeUpdate() == 0) {
                logger.warn("course: {} not added");
                throw new RuntimeException("Course not added");
            }
            logger.info("course: {} added");
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

            logger.info(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                logger.warn("Information not found for courseId: {}", id);
                throw new DataNotFoundException("No course with id:" + id + " exists in database.");
            }
            Course foundCourse = generateCourseFromResultSet(resultSet);
            logger.info("returning found course: {}", foundCourse);
            return foundCourse;
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
