package com.revature.crs.util.auth;

import com.revature.crs.User.User;

import javax.naming.AuthenticationException;
import java.util.Scanner;

public class AuthController {

    private final Scanner scanner;
    private final AuthService authService;

    public AuthController(Scanner scanner, AuthService authService){
        this.scanner=scanner;
        this.authService=authService;
    }

    public User login(User userLoggedIn){
        try{
            if (userLoggedIn != null) throw new RuntimeException("Already logged in");
            System.out.println("Enter email: ");
            String email = scanner.next();

            System.out.println("Enter password: ");
            String password = scanner.next();

            return authService.login(email, password);
        }catch (AuthenticationException | RuntimeException e){
            System.out.println(e.getMessage());
        }
        return userLoggedIn;
    }

}
