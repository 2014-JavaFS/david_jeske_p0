package com.revature.crs.Registration;

import com.revature.crs.util.exceptions.DataNotFoundException;
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
        app.get("/registrations/enrolled", this::getEnrolledRegistrations);
        app.post("/registrations/", this::postNewRegistration);
        app.get("/registrations/{registration_id}", this::getRegistrationById);
        app.put("/registrations", this::putUpdateRegistration);
        app.delete("/registrations/cancel", this::deleteRegistration);
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
            ctx.status(402);
        } else {
            ctx.status(403);
            ctx.result("You can not register for a course at this time.");
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
                ctx.result("Registration updated.");
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }

    public void deleteRegistration(Context ctx) {
        int registrationId = Integer.parseInt(ctx.queryParam("registration_id"));
        log.info("path info registration_id:{}", registrationId);

        int curUser = Integer.parseInt(ctx.header("currentUserId"));
        boolean isFaculty = Boolean.valueOf(ctx.header("isFaculty"));
        log.info("Header info, isFaculty:{}, currentUserId:{}", isFaculty, curUser);
        if (isFaculty || curUser == 0) {
            ctx.result("Unauthorized users cannot cancel registrations");
            ctx.status(403);
            log.info("Unauthorized user tried to cancel registration");
        } else {
            try {
                if (registrationService.delete(registrationId)) {
                    ctx.result("cancelled registration");
                }
                ctx.status(200);
            } catch (DataNotFoundException e) {
                log.warn("registrationId: {} was not found, could not be deleted", registrationId);
                ctx.status(403);
                ctx.result("not found try again.");
            } catch (RuntimeException e) {
                log.warn("Unexpected runtime exception encountered during attempted deletion");
            }
        }
    }

    public void getEnrolledRegistrations(Context ctx) {
        int curUser = Integer.parseInt(ctx.header("currentUserId"));
        boolean isFaculty = Boolean.valueOf(ctx.header("isFaculty"));
        log.info("Header info, isFaculty:{}, currentUserId:{}", isFaculty, curUser);
        if (!isFaculty) {
            try {
                registrationService.findEnrolled(curUser);
            } catch (DataNotFoundException e) {
                log.warn("User has no enrolled registrations");
                ctx.status(404);
                ctx.result("You don't have any enrolled registrations");
                return;
            }
        }
        List<Registration> registrations = registrationService.findEnrolled(curUser);
        ctx.json(registrations);
    }
}
