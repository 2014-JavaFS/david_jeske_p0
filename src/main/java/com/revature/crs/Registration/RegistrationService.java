package com.revature.crs.Registration;

import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Serviceable;

import java.util.List;

public class RegistrationService implements Serviceable<Registration> {
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
    public Registration create(Registration newRegistration) {
        try {
            validateRegistration(newRegistration);
            return newRegistration;
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Registration findById(int id) {
        return registrationRepository.findById(id);
    }

    public boolean update(Registration updatedRegistration) throws InvalidInputException {
        validateRegistration(updatedRegistration);
        return registrationRepository.update(updatedRegistration);
    }

    public void validateRegistration(Registration registration) throws InvalidInputException {
        if (registration == null) throw new InvalidInputException("User is null and not instantiated");
        if (registration.getRegistrationId() <= 0) throw new InvalidInputException("no registrationID");
        if (registration.getCourseId() <= 0) throw new InvalidInputException("no courseID");
        if (registration.getStudentId() <= 0) throw new InvalidInputException("no studentID");
        if (registration.getRegistrationDate() == null) throw new InvalidInputException("no registrationDate");
        //TODO: maybe flesh this out more
    }
}
