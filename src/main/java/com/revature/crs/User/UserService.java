package com.revature.crs.User;

import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Serviceable;

import java.util.List;

public class UserService implements Serviceable<User> {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new DataNotFoundException("No user data available");
        else return users;
    }

    @Override
    public User create(User newUser) throws InvalidInputException {
        validateUser(newUser);
        return userRepository.create(newUser);
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId);
    }

    public User findByLogin(String email, String password) {
        return userRepository.findByLogin(email, password);
    }

    public boolean update(User updatedUser) throws InvalidInputException {
        validateUser(updatedUser);
        return userRepository.update(updatedUser);
    }

    public void validateUser(User user) throws InvalidInputException {
        if (user == null) throw new InvalidInputException("User is null and not instantiated");
        if (!user.getEmail().matches("\\w+(@uni\\.edu)")) {
            //                  \\w+        checks for any amount of non-word characters (alphanumeric and underscore)
            //                  (@uni.edu)  checks that String ends in "@uni.edu" as their assigned email.
            throw new InvalidInputException("Incorrect email entered, remember to use uni.edu address.");
        }
        //TODO: Password requirements?
    }
}
