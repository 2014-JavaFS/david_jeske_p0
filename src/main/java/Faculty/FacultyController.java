package Faculty;

import Course.Course;

public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public void createFaculty() {
        int id = 0;
        String fName = "John";
        String lName = "Doe";
        String email = "JDoe@uni.edu";
        Faculty facultyToAdd = new Faculty(id, fName, lName, email);
        if (facultyService.validateFaculty(facultyToAdd)) {
            facultyService.addFaculty(facultyToAdd);
        }
        //TODO: ACTUALLY WRITE THIS METHOD

    }

    public void addCourseToProfessor(Course course, Faculty professor) {
        for (int i = 0; i < professor.getTaughtCourses().size(); i++) {
            if (professor.getTaughtCourses().get(i).getCourseCode().equals(course.getCourseCode())) {
                //checking if course is already in professor's taughtCourses
                System.out.println(course+" was already assigned.");
                return; //prevents a copy in taughtCourses
            }
        }
        professor.getTaughtCourses().add(course);
    }
}
