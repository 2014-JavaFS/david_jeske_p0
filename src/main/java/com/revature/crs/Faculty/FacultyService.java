package com.revature.crs.Faculty;

import java.util.ArrayList;

public class FacultyService {

    private ArrayList<Faculty> facultyList;

    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
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
