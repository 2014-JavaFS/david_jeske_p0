package com.revature.crs.util;

import java.util.Scanner;

public class CourseRegistrationMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome, please login.");
        System.out.print("Email Address: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

    }
}
