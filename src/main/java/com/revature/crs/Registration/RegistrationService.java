package com.revature.crs.Registration;

import com.revature.crs.Course.CourseController;
import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Serviceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RegistrationService implements Serviceable<Registration> {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> findAll() {
        List<Registration> registrations = registrationRepository.findAll();
        if (registrations.isEmpty()) throw new DataNotFoundException("No registration records available");
        else return registrations;
    }

    @Override
    public Registration create(Registration newRegistration) throws InvalidInputException {
            return registrationRepository.create(newRegistration);
    }

    @Override
    public Registration findById(int id) {
        return registrationRepository.findById(id);
    }

    public boolean update(Registration updatedRegistration) throws InvalidInputException {
        validateRegistration(updatedRegistration);
        return registrationRepository.update(updatedRegistration);
    }

    private void validateRegistration(Registration registration) throws InvalidInputException {
        if (registration == null) throw new InvalidInputException("User is null and not instantiated");
        if (registration.getCourseId() <= 0) throw new InvalidInputException("invalid courseID");
        if (registration.getStudentId() <= 0) throw new InvalidInputException("invalid studentID");
        //TODO: maybe flesh this out more?
    }

    public boolean delete(int id) {
        return registrationRepository.delete(id);
    }
    
    public List<Registration> findEnrolled(int curUser) {
        List<Registration> registrations = registrationRepository.getEnrolled(curUser);
        log.info("searching for user: {}'s registrations", curUser);
        if (registrations.isEmpty()) throw new DataNotFoundException("No Registration Records Found.");
        else return registrations;
    }
}
