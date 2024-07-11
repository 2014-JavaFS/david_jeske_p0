package com.revature.crs.Course;

import com.revature.crs.util.ConnectionFactory;
import com.revature.crs.util.interfaces.Crudable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements Crudable<Course> {

    @Override
    public boolean update(Course updatedObject) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public List<Course> findAll() {
        try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
            List<Course> courses = new ArrayList<>();

            String sql = "select * from courses";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while(rs.next()){
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
    public Course create(Course newCourse) {
        return null;
    }

    @Override
    public Course findById(int id) {
        return null;
    }

    /*
     *  Follows Data Access Object (DAO)
     */
}
