package com.revature.crs.Faculty;

import com.revature.crs.util.interfaces.Serviceable;

import java.util.ArrayList;

public class FacultyService implements Serviceable<Faculty> {

    private ArrayList<Faculty> facultyList = new ArrayList<>();

    @Override
    public Faculty create(Faculty faculty) {
        facultyList.add(faculty);
        return faculty;
    }

    @Override
    public Faculty findById(int userID) {
        for (Faculty f: facultyList){
            if (f.getUserID()==userID) return f;
        }
        return null;
    }

    public Faculty findByLogin(String email, String password){
        for (Faculty f:facultyList){
            if (f.getEmailAddress().equals(email)&&f.getPassword().equals(password)){
                return f;
            }
        }
        return null;
    }

    public boolean validateFaculty(Faculty faculty) {
        //Search to check if faculty is already in facultyList
        for (Faculty f : facultyList) {
            if (faculty.getUserID() == f.getUserID()) return false;
        }
        //TODO: add actual validation
        return true;
    }
}
