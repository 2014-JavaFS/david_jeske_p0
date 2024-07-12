package com.revature.crs.RegistrationRecord;

import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class RegistrationRecordController implements Controller {
    private final RegistrationRecordService regisRecordService;

    public RegistrationRecordController(RegistrationRecordService registrationService) {
        this.regisRecordService = registrationService;
    }

    @Override
    public void registerPaths(Javalin app) {
        app.get("/registration", this::getAllRecords);
    }

    public void getAllRecords(Context ctx) {
        List<RegistrationRecord> records = regisRecordService.findAll();
        ctx.json(records);
    }
}
