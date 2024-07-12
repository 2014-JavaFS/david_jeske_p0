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
import io.javalin.Javalin;

public class CourseRegistrationFrontController {
    public static void main(String[] args) {
        System.out.println("Launching CourseRegis System...");

        Javalin app = Javalin.create();

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService);
        courseController.registerPaths(app);

        User userLoggedIn = null;
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        AuthService authService = new AuthService(userService);
        AuthController authController = new AuthController(authService);
        authController.registerPaths(app);

        app.start(8080);
    }
}
