package Course;

import Faculty.Faculty;
import Student.Student;

public class Course {

    private String courseCode;
    private int sectionNumber;
    private Faculty professor;
    private Student[] enrolled;
    private int creditHours;

    public Course(String courseCode, int sectionNumber, Faculty professor, int creditHours) {
        this.courseCode = courseCode;
        this.sectionNumber = sectionNumber;
        this.professor = professor;
        this.creditHours = creditHours;
    }

    public Course(String courseCode, int sectionNumber, int creditHours) {
        this.courseCode = courseCode;
        this.sectionNumber = sectionNumber;
        this.creditHours = creditHours;
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

    public Student[] getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Student[] enrolled) {
        this.enrolled = enrolled;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    //#endregion

    @Override
    public String toString() {
        if (professor != null) {
            return courseCode + ", Section " + sectionNumber + ", Prof. " + professor.getLastName();
        } else {
            return courseCode + ", Section " + sectionNumber + ", no professor assigned.";
        }
    }
}
