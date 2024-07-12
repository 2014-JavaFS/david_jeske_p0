package com.revature.crs.Course;

import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;

public class CourseController implements Controller {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // principle of REST: uniform interface (PATH in url makes sense)
    @Override
    public void registerPaths(Javalin app) {
        app.get("/courses", this::getAllCourses);
        app.post("/courses", this::postNewCourse);
        app.get("/courses/{course_id}", this::getCourseById); //Path Parameter
        app.put("/courses", this::putUpdateCourse);
    }

    public void getAllCourses(Context ctx) {
        List<Course> courses = courseService.findAll();
        ctx.json(courses);
    }

    public void postNewCourse(Context ctx) {
        Course course = ctx.bodyAsClass(Course.class);
        ctx.json(courseService.create(course));
        ctx.status(HttpStatus.CREATED);
    }

    public void getCourseById(Context ctx) {
        int courseID = Integer.parseInt(ctx.pathParam("course_id"));
        Course courseFound = courseService.findById(courseID);
        ctx.json(courseFound);
    }

    public void putUpdateCourse(Context ctx) {
        Course updatedCourse = ctx.bodyAsClass(Course.class);
        try {
            if (courseService.update(updatedCourse)) {
                ctx.status(HttpStatus.ACCEPTED);
            } else {
                ctx.status(HttpStatus.BAD_REQUEST);
            }
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
    }
}
