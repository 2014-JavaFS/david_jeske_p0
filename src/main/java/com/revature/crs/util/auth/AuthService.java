package com.revature.crs.util.auth;

import com.revature.crs.User.User;
import com.revature.crs.User.UserService;

import javax.naming.AuthenticationException;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService){
        this.userService=userService;
    }

    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByLogin(email, password);
        if (user == null) throw new AuthenticationException("Invalid credentials, please try again.");
        if (user.isFaculty()) System.out.println("welcome faculty");
        else System.out.println("welcome student");
        return user;
    }
}

