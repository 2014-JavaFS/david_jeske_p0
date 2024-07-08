package Faculty;

import java.util.ArrayList;

public class FacultyService {

    private ArrayList<Faculty> facultyList;

    public void createFaculty(Faculty faculty) {
        facultyList.add(faculty);
        if (!faculty.getTaughtCourses().isEmpty()) {
            for (int i = 0; i < faculty.getTaughtCourses().size(); i++) {
                if(faculty.getTaughtCourses().get(i).getProfessor()!=null){
                    //TODO: exception/log for prof being replaced?
                }
                faculty.getTaughtCourses().get(i).setProfessor(faculty);
            }
        }
    }

    public boolean validateFaculty(Faculty faculty) {
        //TODO: ACTUALLY WRITE THIS METHOD
        return true;
    }

}
