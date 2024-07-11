package com.revature.crs.Course;

import com.revature.crs.Faculty.FacultyController;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.interfaces.Serviceable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements Serviceable<Course> {

    //private final FacultyController facultyController;
    private CourseRepository courseRepository;

    private ArrayList<Course> courseList = new ArrayList<>();

//    public CourseService(FacultyController facultyController) {
//        this.facultyController = facultyController;
//    }

    public CourseService(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

//    @Override
//    public Course create(Course course) {
//        courseList.add(course);
//        if (course.getProfessor() != null) {
//            facultyController.addCourseToProfessor(course, course.getProfessor());
//        }
//        return course;
//    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()){
            throw new DataNotFoundException("No Courses Found.");
        }else {
            return courses;
        }
    }

    @Override
    public Course create(Course newObject) {
        return null;
    }

    public Course findById(int id){
        for (Course c:courseList){
            if(c.getCourseID()==id) return c;
        }
        return null;
    }

    public Course findByCourseCode(String courseCode) {
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseCode)) return c;
        }
        return null;
    }

    public Course findByTitle(String courseTitle) {
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseTitle)) return c;
        }
        return null;
    }

    public boolean validateCourse(Course course) {
        //Search to check if course is already in courseList
        for (Course c : courseList) {
            if (course.getCourseCode().equals(c.getCourseCode())) return false;
        }
        //TODO: add actual validation
        return true;
    }
}
