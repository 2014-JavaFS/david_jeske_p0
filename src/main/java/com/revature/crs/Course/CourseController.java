package com.revature.crs.Course;

import com.revature.crs.Faculty.Faculty;
import com.revature.crs.Student.Student;

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public void createCourse() {
        String subject = "MATH";
        short courseNum = 101;
        short sectionNum = 1;
        String courseTitle = "Intro Math";
        short creditHours = 3;
        short capacity = 10;
        Course courseToAdd = new Course(subject, courseNum, sectionNum, courseTitle, creditHours, capacity);
        if (courseService.validateCourse(courseToAdd)) {
            courseService.addCourse(courseToAdd);
        }
        //TODO: ACTUALLY WRITE THIS METHOD
    }

    public void addProfessorToCourse(Faculty professor, Course course) {
        course.setProfessor(professor);
        //TODO: add a check for if this replaces a prof, and log it or something?
    }

    public void addStudentToCourse(Student student, Course course) {
        if (course.getEnrolled().size() < course.getCapacity()) {
            course.getEnrolled().add(student);
            student.getEnrolledCourses().add(course);
        } else {
            System.out.println("That course is full!");
        }
    }

}
