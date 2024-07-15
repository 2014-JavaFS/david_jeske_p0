package com.revature.crs.Registration;

import java.time.LocalDate;

public class Registration {
    private int registrationID;
    private int courseID;
    private int studentID;
    private LocalDate registrationDate;

    public Registration(int registrationID, int courseID, int studentID, LocalDate registrationDate) {
        this.registrationID = registrationID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.registrationDate = registrationDate;
    }

    public Registration(int registrationID, int courseID, int studentID) {
        this.registrationID = registrationID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.registrationDate = LocalDate.now();
    }

    public Registration() {
    }

    //#region Getters & Setters
    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    //endregion

    @Override
    public String toString() {
        return ("RegisID: " + registrationID +
                ", courseID: " + courseID +
                ", studentID: " + studentID +
                ", record made: " + registrationDate.toString());
    }
}
