package com.revature.crs.util.auth;

import com.revature.crs.util.interfaces.Controller;
import com.revature.crs.User.User;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import javax.naming.AuthenticationException;

import static com.revature.crs.util.CourseRegistrationFrontController.logger;

public class AuthController implements Controller {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.post("/login", this::postLogin);
    }

    private void postLogin(Context ctx){
        String email = ctx.queryParam("email");
        String password = ctx.queryParam("password");

        try {
            User user = authService.login(email, password);
            ctx.header("userID", String.valueOf(user.getUserID()));
            logger.info("attempting to set header to: {}", user.isFaculty());
            ctx.header("isFaculty", String.valueOf(user.isFaculty()));
            logger.info("header set to: {}", ctx.header("isFaculty"));
            ctx.status(200);
        } catch (AuthenticationException e) {
            ctx.status(HttpStatus.UNAUTHORIZED);
        }
    }
}
