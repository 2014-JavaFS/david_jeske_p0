package com.revature.crs.Course;

import com.revature.crs.Faculty.FacultyController;
import com.revature.crs.util.interfaces.Serviceable;

import java.util.ArrayList;

public class CourseService implements Serviceable<Course> {

    private final FacultyController facultyController;

    private ArrayList<Course> courseList = new ArrayList<>();

    public CourseService(FacultyController facultyController) {
        this.facultyController = facultyController;
    }

    @Override
    public Course create(Course course) {
        courseList.add(course);
        if (course.getProfessor() != null) {
            facultyController.addCourseToProfessor(course, course.getProfessor());
        }
        return course;
    }

    public Course findById(int id){
        for (Course c:courseList){
            if(c.getCourseID()==id) return c;
        }
        return null;
    }

    public Course findByCode(String courseCode) {
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
