package com.revature.crs.User;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.get("/users", this::getAllUsers);
        app.post("/users", this::postNewUser);
        app.get("/users/{user_id}", this::getUserById);
        app.put("/users", this::putUpdateUser);
    }

    public void getAllUsers(Context ctx) {
        List<User> users = userService.findAll();
        ctx.json(users);
    }

    public void postNewUser(Context ctx) throws InvalidInputException {
        //TODO restrict to unlogged-in users? only for registering new account?
        User user = ctx.bodyAsClass(User.class);
        ctx.json(userService.create(user));
        ctx.status(HttpStatus.CREATED);
    }

    public void getUserById(Context ctx) {
        int userID = Integer.parseInt(ctx.pathParam("user_id"));
        User userFound = userService.findById(userID);
        ctx.json(userFound);
    }

    public void putUpdateUser(Context ctx) {
        User updatedUser = ctx.bodyAsClass(User.class);
        try {
            if (userService.update(updatedUser)) {
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
