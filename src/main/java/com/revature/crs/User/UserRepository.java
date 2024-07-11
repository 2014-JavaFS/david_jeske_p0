package com.revature.crs.User;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Crudable;

import java.util.List;

public class UserRepository implements Crudable<User> {
    @Override
    public boolean update(User updatedUser) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User create(User newObject) throws InvalidInputException {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    public User findByLogin(String email, String password){
        return null;
    }
}
