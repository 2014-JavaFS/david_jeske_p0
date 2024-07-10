package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.User.User;

import java.util.Set;

public class Student extends User {

    private Set<Course> enrolledCourses;

    public Student(int userID, String firstName, String lastName, String emailAddress, String password) {
        super(userID, firstName, lastName, emailAddress, password);
    }

    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public int getEnrolledCreditHours(){
        int hours = 0;
        for (Course c : this.enrolledCourses){
            hours += c.getCreditHours();
        }
        return hours;
    }
}
