package Faculty;

import Course.Course;

import java.util.ArrayList;

public class Faculty {

    private int facultyID;
    private String firstName;
    private String lastName;
    private ArrayList<Course> taughtCourses;
    private String email;

    public Faculty(int facultyID, String firstName, String lastName, String email){
        this.facultyID=facultyID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

    //#region Getters & Setters
    public int getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<Course> getTaughtCourses() {
        return taughtCourses;
    }

    public void setTaughtCourses(ArrayList<Course> taughtCourses) {
        this.taughtCourses = taughtCourses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //#endregion

    @Override
    public String toString() {
        return (firstName + " " + lastName + ", ID: " + facultyID);
    }
}
