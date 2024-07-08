package Course;

import Faculty.FacultyController;

import java.util.ArrayList;

public class CourseService {

    private final FacultyController facultyController;

    private ArrayList<Course> courseList;

    public CourseService(FacultyController facultyController) {
        this.facultyController = facultyController;
    }

    public void addCourse(Course course) {
        courseList.add(course);
        if (course.getProfessor() != null) {
            facultyController.addCourseToProfessor(course, course.getProfessor());
        }
    }

    public boolean validateCourse(Course course) {
        //Search to check if course is already in courseList
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(course.getCourseCode())) {
                return false;
            }
        }
        //TODO: add actual validation
        return true;
    }
}
