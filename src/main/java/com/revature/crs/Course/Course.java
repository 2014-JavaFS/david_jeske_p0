package com.revature.crs.Course;

public class Course {
    private int courseID;
    private String courseCode;
    private String courseTitle;
    private short creditHours;
    private short capacity;
    private short enrolled;
    private int professor;

    public Course(int courseID, String courseCode, String courseTitle, short creditHours, short capacity, short enrolled, int professor) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.professor = professor;
    }

    public Course(int courseID, String courseCode, String courseTitle, short creditHours, short capacity, int professor) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.enrolled = 0;
        this.professor = professor;
    }

    public Course(int courseID, String courseCode, String courseTitle, short creditHours, short capacity, short enrolled) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.enrolled = enrolled;
    }

    public Course(int courseID, String courseCode, String courseTitle, short creditHours, short capacity) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public Course() {
    }

    //#region Getters & Setters
    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public short getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(short creditHours) {
        this.creditHours = creditHours;
    }

    public short getCapacity() {
        return capacity;
    }

    public void setCapacity(short capacity) {
        this.capacity = capacity;
    }

    public short getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(short enrolled) {
        this.enrolled = enrolled;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }
    //#endregion

    @Override
    public String toString() {
        return getCourseCode() + ": " + courseTitle;
    }
}
