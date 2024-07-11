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
    public boolean delete(int courseCode) {
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
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                Course course = new Course();

                course.setCourseID(rs.getInt("course_id"));
                //rest of record's attributes

                courses.add(course);
            }

            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course create(Course newCourse) throws InvalidInputException {
        try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            //SQL for insert

            //DO NOT USE STATEMENT FOR DML (insert, update, delete)
            String sql = "insert into courses(course_id, course_code, course_title, credit_hours, capacity) values(?,?,?,?,?,?,?)";
            //PreparedStatement SANITIZES user input before execution
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //SQL 1-index, NOT 0-index
            preparedStatement.setInt(1, newCourse.getCourseID());
            preparedStatement.setString(2, "MATH");

            if (preparedStatement.executeUpdate(sql) == 0) {
                throw new RuntimeException("new record was not inserted");
            }
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
                throw new DataNotFoundException("No course with that id " + id + " exists in database.");
            }
            return generateCourseFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Course generateCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setCourseID(resultSet.getInt("course_id"));
        course.setCourseCode(resultSet.getString("course_code"));
        course.setCourseTitle(resultSet.getString("course_title"));
        course.setCreditHours(resultSet.getShort("credit_hours"));
        course.setCapacity(resultSet.getShort("capacity"));
        return course;
    }
}
