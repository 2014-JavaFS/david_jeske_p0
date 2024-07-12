package com.revature.crs.Course;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Crudable<Course> {
    /*
     *  Follows Data Access Object (DAO)
     */

    @Override
    public boolean update(Course updatedObject) {

        return false;
    }

    @Override
    public boolean delete(int courseID) {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {

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
            String sql = "insert into courses values(default,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, newCourse.getCourseCode());
            preparedStatement.setString(2, newCourse.getCourseTitle());
            preparedStatement.setShort(3, newCourse.getCreditHours());
            preparedStatement.setShort(4, newCourse.getCapacity());
            preparedStatement.setShort(5, newCourse.getEnrolled());
            preparedStatement.setInt(6, newCourse.getProfessor());
            if (preparedStatement.executeUpdate(sql) == 0) throw new RuntimeException("Course not added");
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

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new DataNotFoundException("No course with id:" + id + " exists in database.");
            }
            return generateCourseFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Course generateCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseCode(resultSet.getString("course_code"));
        course.setCourseTitle(resultSet.getString("course_title"));
        course.setCreditHours(resultSet.getShort("credit_hours"));
        course.setCapacity(resultSet.getShort("capacity"));
        course.setEnrolled(resultSet.getShort("enrolled"));
        course.setProfessor(resultSet.getInt("professor"));
        return course;
    }
}
