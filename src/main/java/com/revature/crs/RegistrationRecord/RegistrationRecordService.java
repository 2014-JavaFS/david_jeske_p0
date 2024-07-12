package com.revature.crs.RegistrationRecord;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Serviceable;

import java.util.List;

public class RegistrationRecordService implements Serviceable<RegistrationRecord> {
    private RegistrationRecordRepository registEntryRepo;

    public RegistrationRecordService(RegistrationRecordRepository registrationEntryRepository) {
        this.registEntryRepo = registrationEntryRepository;
    }

    @Override
    public List<RegistrationRecord> findAll() {
        return List.of();
    }

    @Override
    public RegistrationRecord create(RegistrationRecord newObject) throws InvalidInputException {
        return null;
    }

    @Override
    public RegistrationRecord findById(int id) {
        return null;
    }
}
