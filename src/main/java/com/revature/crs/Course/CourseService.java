package com.revature.crs.Course;

import com.revature.crs.Faculty.FacultyController;

import java.util.ArrayList;

public class CourseService {

    private final FacultyController facultyController;

    private ArrayList<Course> courseList;

    public CourseService(FacultyController facultyController) {
        this.facultyController = facultyController;
    }

    public Course findByCode(String courseCode) {
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseCode)) return c;
        }
        return null;
    }

    public void addCourse(Course course) {
        courseList.add(course);
        if (course.getProfessor() != null) {
            facultyController.addCourseToProfessor(course, course.getProfessor());
        }
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
