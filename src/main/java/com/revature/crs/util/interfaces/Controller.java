package com.revature.crs.util.interfaces;

import io.javalin.Javalin;

public interface Controller {
    void registerPaths(Javalin app);
}
