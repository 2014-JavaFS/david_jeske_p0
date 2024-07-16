package com.revature.crs.Registration;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;


public class RegistrationController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.get("/registrations", this::getAllRegistrations);
        app.post("/registrations/", this::postNewRegistration);
        app.get("/registrations/{registration_id}", this::getRegistrationById);
        app.put("/registrations", this::putUpdateRegistration);
        app.delete("/registrations/cancel/{registration_id}", this::deleteRegistration);
    }

    public void getAllRegistrations(Context ctx) {
        List<Registration> registrations = registrationService.findAll();
        ctx.json(registrations);
    }

    public void postNewRegistration(Context ctx) throws InvalidInputException {
        int courseId = Integer.parseInt(ctx.queryParam("courseId"));
        int studentId = Integer.parseInt(ctx.header("currentUserId"));
        boolean isFaculty = Boolean.parseBoolean(ctx.header("isFaculty"));
        log.info("path param courseId: {}", courseId);
        log.info("header info isFaculty: {}; studentId: {}", isFaculty, studentId);

        if (!isFaculty) {
            log.info("studentId: {} is registering for courseId: {}", studentId, courseId);
            registrationService.create(new Registration(0, courseId, studentId, LocalDate.now()));
        }
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

    public void deleteRegistration(Context ctx) {
        int registrationId = Integer.parseInt(ctx.pathParam("registration_id"));
        registrationService.delete(registrationId);
        log.info("registration deleted");
    }
}
