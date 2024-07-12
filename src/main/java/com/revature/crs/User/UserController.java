package com.revature.crs.User;

import com.revature.crs.util.exceptions.InvalidInputException;

import java.util.Scanner;

public class UserController {
    private final UserService userService;
    private final Scanner scanner;
    private int count = 0;

    public UserController(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public void register() {
        System.out.println("Please fill out the following fields:\nFirst Name: ");
        String firstName = scanner.next();
        System.out.println("Last Name: ");
        String lastName = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        boolean isFaculty = false;

        User userToAdd = new User(count, firstName, lastName, email, password, isFaculty);
        try {
            userService.create(userToAdd);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            //Logging?
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Unexpected RuntimeException encountered");
        }
    }

    public void update() {
    }


}
