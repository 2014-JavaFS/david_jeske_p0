package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import com.revature.crs.util.User;

import java.util.ArrayList;

public class Faculty extends User {

    private ArrayList<Course> taughtCourses;

    public Faculty(int userID, String firstName, String lastName, String emailAddress, String password) {
        super(userID, firstName, lastName, emailAddress, password);
    }

    public ArrayList<Course> getTaughtCourses() {
        return taughtCourses;
    }

    public void setTaughtCourses(ArrayList<Course> taughtCourses) {
        this.taughtCourses = taughtCourses;
    }
}
