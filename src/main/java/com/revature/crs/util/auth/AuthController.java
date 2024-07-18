package com.revature.crs.util.auth;

import com.revature.crs.User.User;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import javax.security.sasl.AuthenticationException;

import static com.revature.crs.util.CourseRegistrationFrontController.logger;

public class AuthController implements Controller {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.post("/login", this::postLogin);
        app.post("/logout", this::postLogout);
    }

    private void postLogin(Context ctx) {
        String email = ctx.queryParam("email");
        String password = ctx.queryParam("password");
        try {
            User user = authService.login(email, password);
            ctx.header("currentUserId", String.valueOf(user.getUserID()));
            ctx.header("isFaculty", String.valueOf(user.isFaculty()));
            logger.info("Logged in: {}", user);
            ctx.status(202);
            ctx.result("Welcome, " + user.getFirstName());
            //I feel like there's a conventional ordering to these I don't know
        } catch (AuthenticationException e) {
            ctx.status(HttpStatus.UNAUTHORIZED);
            ctx.header("currentUserId", "0");
            ctx.header("isFaculty", "false");
            ctx.result(e.getMessage());
            logger.warn("Incorrect or invalid login credentials entered.");
        }
    }

    private void postLogout(Context ctx) {
        ctx.header("currentUserId", "0");
        ctx.header("isFaculty", "false");
        ctx.result("Logged Out");
        logger.warn("Clearing logged in information.");
    }
}
