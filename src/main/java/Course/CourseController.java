package Course;

import Faculty.Faculty;
import Student.Student;

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public void createCourse() {
        String courseCode = "MATH101";
        int creditHours = 3;
        int capacity = 10;
        Course courseToAdd = new Course(courseCode, creditHours, capacity);
        if (courseService.validateCourse(courseToAdd)) {
            courseService.addCourse(courseToAdd);
        }
        //TODO: ACTUALLY WRITE THIS METHOD
    }

    public void addProfessorToCourse(Faculty professor, Course course) {
        course.setProfessor(professor);
        //TODO: add a check for if this replaces a prof, and log it or something?
    }

    public void addStudentToCourse(Student student, Course course) {
        if (course.getEnrolled().size() < course.getCapacity()) {
            course.getEnrolled().add(student);
            student.getEnrolledCourses().add(course);
        } else {
            System.out.println("That course is full!");
        }
    }

}
