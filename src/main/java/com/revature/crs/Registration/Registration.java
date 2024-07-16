package com.revature.crs.Registration;

import java.time.LocalDate;

public class Registration {
    private int registrationId;
    private int courseId;
    private int studentId;
    private LocalDate registrationDate;

    public Registration(int registrationId, int courseId, int studentId, LocalDate registrationDate) {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.registrationDate = registrationDate;
    }

    public Registration() {
    }

    //#region Getters & Setters
    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
        return ("registrationId: " + registrationId +
                ", courseId: " + courseId +
                ", studentId: " + studentId +
                ", date: " + registrationDate.toString());
    }
}
