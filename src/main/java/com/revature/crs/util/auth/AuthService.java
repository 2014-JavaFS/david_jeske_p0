package com.revature.crs.util.auth;

import com.revature.crs.Faculty.FacultyService;
import com.revature.crs.Student.StudentService;
import com.revature.crs.util.User;

public class AuthService {

    private final StudentService studentService;
    private final FacultyService facultyService;

    public AuthService(StudentService studentService, FacultyService facultyService){
        this.studentService=studentService;
        this.facultyService=facultyService;
    }

    public User login(String email, String password){

        return null;
    }
}

