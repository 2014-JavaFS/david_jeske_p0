package com.revature.crs.RegistrationRecord;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.util.List;

public class RegistrationRecordRepository implements Crudable<RegistrationRecord> {


    @Override
    public boolean update(RegistrationRecord updatedObject) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
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
