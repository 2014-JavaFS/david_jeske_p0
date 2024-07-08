package Faculty;

import java.util.ArrayList;

public class FacultyService {

    private ArrayList<Faculty> facultyList;

    public void addFaculty(Faculty faculty) {
        facultyList.add(faculty);
    }

    public boolean validateFaculty(Faculty faculty) {
        //Search to check if faculty is already in facultyList
        for (int i = 0; i < facultyList.size(); i++) {
            if (facultyList.get(i).getFacultyID() == faculty.getFacultyID()) {
                System.out.println(faculty + " already in system");
                return false;
            }
        }
        //TODO: add actual validation
        return true;
    }

}
