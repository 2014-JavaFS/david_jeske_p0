package com.revature.crs.User;

import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;

public class UserController implements Controller {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerPaths(Javalin app) {

    }
}
