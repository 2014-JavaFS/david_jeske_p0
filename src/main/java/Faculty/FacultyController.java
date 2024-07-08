package Faculty;

import Course.Course;

public class FacultyController {

    public void addCourseToProfessor(Course course, Faculty professor) {
        professor.getTaughtCourses().add(course);
    }
}
