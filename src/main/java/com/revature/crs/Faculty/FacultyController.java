package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public void createFaculty() {
        int id = 0;
        String fName = "John";
        String lName = "Doe";
        String email = "JDoe@uni.edu";
        String password = "1234";
        Faculty facultyToAdd = new Faculty(id, fName, lName, email, password);
        if (facultyService.validateFaculty(facultyToAdd)) facultyService.addFaculty(facultyToAdd);
        //TODO: ACTUALLY WRITE THIS METHOD

    }

    public void addCourseToProfessor(Course course, Faculty professor) {
        for (Course c : professor.getTaughtCourses()) {
            if (course.getCourseCode().equals(c.getCourseCode())) {
                System.out.println(course + "was already added.");
                return;
            }
        }
        professor.getTaughtCourses().add(course);


    }
}
