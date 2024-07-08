package Course;

import Faculty.Faculty;

import java.util.ArrayList;

public class CourseService {

    private ArrayList<Course> courses;

    public void addProfessorToCourse(Faculty professor, Course course) {
        course.setProfessor(professor);
    }
}
