package com.revature.crs.util;

import com.revature.crs.Course.CourseController;
import com.revature.crs.Course.CourseRepository;
import com.revature.crs.Course.CourseService;
import com.revature.crs.Registration.RegistrationController;
import com.revature.crs.Registration.RegistrationRepository;
import com.revature.crs.Registration.RegistrationService;
import com.revature.crs.User.UserController;
import com.revature.crs.User.UserRepository;
import com.revature.crs.User.UserService;
import com.revature.crs.util.auth.AuthController;
import com.revature.crs.util.auth.AuthService;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRegistrationFrontController {

    public static final Logger logger = LoggerFactory.getLogger(CourseRegistrationFrontController.class);

    public static void main(String[] args) {
        logger.info("Course Registration System launching...");
        Javalin app = Javalin.create(config -> {
          config.jsonMapper(new JavalinJackson());
        });

        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseController courseController = new CourseController(courseService);
        courseController.registerPaths(app);

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);
        userController.registerPaths(app);

        RegistrationRepository regisRecordRepository = new RegistrationRepository();
        RegistrationService regisRecordService = new RegistrationService(regisRecordRepository);
        RegistrationController regisRecordController = new RegistrationController(regisRecordService);
        regisRecordController.registerPaths(app);

        AuthService authService = new AuthService(userService);
        AuthController authController = new AuthController(authService);
        authController.registerPaths(app);

        app.start(8080);
    }
}
