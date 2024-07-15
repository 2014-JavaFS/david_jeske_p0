package com.revature.crs.Registration;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

public class RegistrationController implements Controller {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.get("/registrations", this::getAllRegistrations);
        app.post("/registrations", this::postNewUser);
        app.get("/registrations/{registration_id}", this::getRegistrationById);
        app.put("/registrations", this::putUpdateRegistration);
    }

    public void getAllRegistrations(Context ctx) {
        List<Registration> registrations = registrationService.findAll();
        ctx.json(registrations);
    }
    
    public void postNewUser(Context ctx) {
        Registration registration = ctx.bodyAsClass(Registration.class);
        ctx.json(registrationService.create(registration));
        ctx.status(HttpStatus.CREATED);
    }

    public void getRegistrationById(Context ctx) {
        int registrationId = Integer.parseInt(ctx.pathParam("registration_id"));
        Registration registrationFound = registrationService.findById(registrationId);
        ctx.json(registrationFound);
    }

    public void putUpdateRegistration(Context ctx) {
        Registration updatedRegistration = ctx.bodyAsClass(Registration.class);
        try {
            if (registrationService.update(updatedRegistration)) {
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
