package com.revature.crs.Student;

import com.revature.crs.Faculty.Faculty;
import com.revature.crs.util.interfaces.Serviceable;

import java.util.ArrayList;

public class StudentService implements Serviceable<Student> {

    private ArrayList<Student> studentList = new ArrayList<>();

    @Override
    public Student create(Student student) {
        studentList.add(student);
        return student;
    }

    @Override
    public Student findById(int userID) {
        for (Student s : studentList) {
            if (s.getUserID() == userID) return s;
        }
        return null;
    }

    public Student findByLogin(String email, String password) {
        for (Student s : studentList) {
            if (s.getEmailAddress().equals(email) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return null;
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
