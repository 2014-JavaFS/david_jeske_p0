package com.revature.crs.Course;

import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Controller;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // principle of REST: uniform interface (PATH in url makes sense)
    @Override
    public void registerPaths(Javalin app) {
        app.get("/courses", this::getAllCourses);
        app.post("/courses", this::postNewCourse);
        app.get("/courses/available", this::getAvailableCourses);
        app.get("/courses/enrolled", this::getEnrolledCourses);
        app.get("/courses/{course_id}", this::getCourseById); //Path Parameter
        app.put("/courses", this::putUpdateCourse);
        app.delete("/courses/{course_id}", this::deleteCourse);
    }

    public void getAllCourses(Context ctx) {
        List<Course> courses = courseService.findAll();
        ctx.json(courses);
    }

    public void postNewCourse(Context ctx) throws InvalidInputException {
        boolean isFaculty = Boolean.valueOf(ctx.header("isFaculty"));
        log.info("isFaculty from header: {}", isFaculty);
        if (isFaculty) {
            log.info("logged in user is faculty, creating Course...");
            Course course = ctx.bodyAsClass(Course.class);
            ctx.json(courseService.create(course));
            ctx.status(HttpStatus.CREATED);
            return;
        }
        log.info("logged in user is not faculty");
        ctx.status(403);
        ctx.result("You do not have permission to create a new course.");
    }

    public void getCourseById(Context ctx) {
        int courseId = Integer.parseInt(ctx.pathParam("course_id"));
        log.info("CourseId: {} was sent through path parameter", courseId);
        try {
            Course courseFound = courseService.findById(courseId);
            log.info("Course to be returned: {}", courseFound);
            ctx.json(courseFound);
        } catch (DataNotFoundException e) {
            log.warn("Information for courseId: {} was not found", courseId);
        } catch (RuntimeException e) {
            log.warn("Unexpected runtime exception");
        }
    }

    public void putUpdateCourse(Context ctx) {
        Course updatedCourse = ctx.bodyAsClass(Course.class);
        boolean isFaculty = Boolean.valueOf(ctx.header("isFaculty"));
        log.info("isFaculty from header: {}", isFaculty);
        if (isFaculty) {
            try {
                if (courseService.update(updatedCourse)) {
                    ctx.status(HttpStatus.ACCEPTED);
                } else {
                    ctx.status(HttpStatus.BAD_REQUEST);
                }
            } catch (InvalidInputException e) {
                e.printStackTrace();
            }
            return;

        }
        log.info("logged in user is not faculty");
        ctx.status(403);
        ctx.result("You do not have permission to update course info.");
    }

    public void deleteCourse(Context ctx) {
        int courseId = Integer.parseInt(ctx.pathParam("course_id"));
        log.info("CourseId: {} was sent through path parameter", courseId);
        boolean isFaculty = Boolean.valueOf(ctx.header("isFaculty"));
        log.info("isFaculty from header: {}", isFaculty);
        if (isFaculty) {
            try {
                courseService.delete(courseId);
            } catch (DataNotFoundException e) {
                log.warn("courseId: {} was not found, could not be deleted", courseId);
            } catch (RuntimeException e) {
                log.warn("Unexpected runtime exception encountered during attempted deletion");
            }
            return;
        }
        log.info("logged in user is not faculty");
        ctx.status(403);
        ctx.result("You do not have permission to delete a course.");
    }

    public void getAvailableCourses(Context ctx) {
        List<Course> courses = courseService.findAvailable();
        ctx.json(courses);
    }

    public void getEnrolledCourses(Context ctx) {
        int curUser = Integer.parseInt(ctx.header("currentUserId"));
        List<Course> courses = courseService.findEnrolled(curUser);
        ctx.json(courses);
    }

}
