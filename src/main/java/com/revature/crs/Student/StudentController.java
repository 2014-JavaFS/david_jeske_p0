package com.revature.crs.Student;

import com.revature.crs.Course.Course;

public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void createStudent() {
        int id = 0;
        String fName = "Jane";
        String lName = "Smith";
        String email = "JSmith@uni.edu";
        String password = "1234";
        Student studentToAdd = new Student(id, fName, lName, email, password);
        if (studentService.validateStudent(studentToAdd)) studentService.addStudent(studentToAdd);
        //TODO: ACTUALLY WRITE THIS METHOD
    }

    public void addCourseToStudent(Course course, Student student) {
        for (Course c : student.getEnrolledCourses()) {
            //making sure student isn't enrolled in multiple sections of the same course, also catches full duplicates
            if (course.getSubject().equals(c.getSubject()) && course.getCourseNum() == c.getCourseNum()) {
                System.out.println(student + " is already enrolled in " + course);
            } else {
                //checks if s
                if (student.getEnrolledCreditHours() + course.getCreditHours() >= 20) {
                    System.out.println("This course will exceed the maximum credit hours allowed per semester " +
                            "for " + student + ", please contact an academic advisor to apply for exemption.");
                } else {
                    student.getEnrolledCourses().add(course);
                }
            }

        }
    }
}
