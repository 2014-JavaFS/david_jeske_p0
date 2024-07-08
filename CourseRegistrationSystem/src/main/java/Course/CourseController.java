package Course;

import Faculty.Faculty;

public class CourseController {

    public void addProfessorToCourse(Faculty professor, Course course) {
        course.setProfessor(professor);
    }
}
