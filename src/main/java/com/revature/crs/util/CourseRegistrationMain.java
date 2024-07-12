package com.revature.crs.util;

import com.revature.crs.Course.CourseController;
import com.revature.crs.Course.CourseRepository;
import com.revature.crs.Course.CourseService;
import com.revature.crs.User.User;
import com.revature.crs.User.UserController;
import com.revature.crs.User.UserRepository;
import com.revature.crs.User.UserService;
import com.revature.crs.util.auth.AuthController;
import com.revature.crs.util.auth.AuthService;

import java.util.Scanner;

public class CourseRegistrationMain {
    public static void main(String[] args) {
        System.out.println("CourseRegistrationSystem now running...");
        //Variables to instantiate on start
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService, scanner);

        User userLoggedIn = null;
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService, scanner);

        AuthService authService = new AuthService(userService);
        AuthController authController = new AuthController(authService, scanner);

        do {
            //ACCOUNT OPTIONS
            System.out.println("Welcome" + (userLoggedIn == null ? "!" :
                    (", " + userLoggedIn.getFirstName() + "!")));

            //LOGIN OPTIONS
            System.out.println("1. Login");
            System.out.println("2. Register account (new student)");

            if (userLoggedIn != null) {
                //FACULTY OPTIONS
                if (userLoggedIn.isFaculty()) {
                    System.out.println("3. Add new course");
                    System.out.println("4. Update a course");
                    System.out.println("5. Delete a course");
                }

                //STUDENT OPTIONS
                if (!userLoggedIn.isFaculty()) {
                    System.out.println("6. View registered courses");
                    System.out.println("7. Register for a course");
                    System.out.println("8. Cancel registered course");
                }

                //LOGGED IN OPTIONS
                System.out.println("9. Search courses");
                System.out.println("10. Logout");
            }
            System.out.println("Please enter menu selection, or 0 to exit:");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid menu selection");
                scanner.nextLine();
                continue;
            }
            menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 0:
                    System.out.println("Thanks for using Course Registration System.");
                    break;
                case 1:
                    System.out.println("Logging in...");
                    userLoggedIn = authController.login(userLoggedIn);
                    break;
                case 2:
                    System.out.println("Registering new account (student)...");
                    userController.register();
                    break;
                case 3:
                    System.out.println("Creating new course...");
                    courseController.addCourse();
                    break;
                case 4:
                    System.out.println("Updating course...");
                    courseController.updateCourse();
                    break;
                case 5:
                    System.out.println("Deleting course...");
                    courseController.deleteCourse();
                    break;
                case 6:
                    System.out.println("Viewing registered courses...");
                    //view registration
                    break;
                case 7:
                    System.out.println("Register a new course...");
                    //create registration
                    break;
                case 8:
                    System.out.println("Canceling registration...");
                    //cancel registration
                    break;
                case 9:
                    System.out.println("Searching courses...");
                    courseController.searchCourses();
                    break;
                case 10:
                    System.out.println("Logging out...");
                    userLoggedIn = null;
                    break;
                default:
                    System.out.println("Invalid selection, place enter the number of a valid menu option.");
            }
        } while (menuChoice != 0);
    }
}
