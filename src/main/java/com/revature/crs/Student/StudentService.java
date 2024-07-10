package com.revature.crs.Student;

import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> studentList;

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public boolean validateStudent(Student student) {
        //Search to check if student is already in studentList
        for (Student s : studentList) {
            if (student.getUserID() == s.getUserID()) return false;
        }
        //TODO: add actual validation
        return true;
    }
}
