package com.revature.crs.Course;

public class Course {
    private int courseId;
    private String courseCode;
    private String courseTitle;
    private short capacity;
    private short enrolled;
    private int professor;

    public Course(int courseId, String courseCode, String courseTitle, short capacity, short enrolled, int professor) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.enrolled = enrolled;
        this.professor = professor;
    }

    public Course(int courseId, String courseCode, String courseTitle, short capacity, int professor) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.enrolled = 0;
        this.professor = professor;
    }

    public Course(int courseId, String courseCode, String courseTitle, short capacity, short enrolled) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.enrolled = enrolled;
    }

    public Course(int courseId, String courseCode, String courseTitle, short capacity) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public Course() {
    }

    //#region Getters & Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
