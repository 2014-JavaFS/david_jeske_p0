package Student;

import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;

import Course.Course;

public class Student {

    private int studentID;
    private String firstName;
    private String lastName;
    private ArrayList<Course> enrolledCourses;
    private String emailAddress;

    public Student(int studentID, String firstName, String lastName, String emailAddress){
        this.studentID=studentID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.emailAddress = emailAddress;
    }

    //#region Getters & Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public ArrayList<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    //#endregion

    public String toString() {
        return (firstName + " " + lastName + ", ID: " + studentID);
    }

    public int getEnrolledCreditHours() {
        int hourSum = 0;
        for (int i = 0; i < enrolledCourses.size(); i++) {
            hourSum += enrolledCourses.get(i).getCreditHours();
        }
        return hourSum;
    }
}
