package Faculty;

import Course.Course;

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService){
        this.facultyService=facultyService;
    }

    public void addFaculty(){
        //TODO: ACTUALLY WRITE THIS METHOD

    }

    public void addCourseToProfessor(Course course, Faculty professor) {
        professor.getTaughtCourses().add(course);
    }
}
