package com.revature.crs.Course;

import com.revature.crs.Faculty.Faculty;
import com.revature.crs.Student.Student;

import java.util.ArrayList;

public class Course {

    private int courseID;
    private String subject;
    private short courseNum;
    private short sectionNum;
    private String courseTitle;
    private short creditHours;
    private short capacity;
    private Faculty professor;

    private ArrayList<Student> enrolled;

    public Course(int courseID, String subject, short courseNumber, short sectionNumber, String courseTitle, short creditHours, short capacity, Faculty professor) {
        this.courseID=courseID;
        this.subject = subject;
        this.courseNum = courseNumber;
        this.sectionNum = sectionNumber;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
        this.professor = professor;
    }

    public Course(int courseID, String subject, short courseNumber, short sectionNumber, String courseTitle, short creditHours, short capacity) {
        this.courseID=courseID;
        this.subject = subject;
        this.courseNum = courseNumber;
        this.sectionNum = sectionNumber;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.capacity = capacity;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public short getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(short courseNum) {
        this.courseNum = courseNum;
    }

    public short getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(short sectionNum) {
        this.sectionNum = sectionNum;
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

    public Faculty getProfessor() {
        return professor;
    }

    public void setProfessor(Faculty professor) {
        this.professor = professor;
    }

    public ArrayList<Student> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(ArrayList<Student> enrolled) {
        this.enrolled = enrolled;
    }

    //#endregion

    public String getCourseCode() {
        return this.subject + this.courseNum + "-" + this.sectionNum;
    }

    @Override
    public String toString() {
        return getCourseCode() + ": " + courseTitle + ", Prof. " + this.professor.getLastName();
    }
}
