package Course;

import Faculty.Faculty;
import Student.Student;

import java.util.ArrayList;

public class Course {

    private String courseCode;
    private Faculty professor;
    private ArrayList<Student> enrolled;
    private int creditHours;
    private int capacity;

    public Course(String courseCode, Faculty professor, int creditHours, int capacity) {
        this.courseCode = courseCode;
        this.professor = professor;
        this.creditHours = creditHours;
        this.capacity = capacity;
    }

    public Course(String courseCode, int creditHours, int capacity) {
        this.courseCode = courseCode;
        this.creditHours = creditHours;
        this.capacity = capacity;
    }

    //#region Getters & Setters
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //#endregion

    @Override
    public String toString() {
        if (professor != null) {
            return courseCode + ", Prof. " + professor.getLastName();
        } else {
            return courseCode + ", no professor assigned.";
        }
    }
}
