package com.revature.crs.Course;

import com.revature.crs.util.exceptions.DataNotFoundException;
import com.revature.crs.util.exceptions.InvalidInputException;
import com.revature.crs.util.interfaces.Serviceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseService implements Serviceable<Course> {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);
    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) throw new DataNotFoundException("No Courses Found.");
        else return courses;
    }

    @Override
    public Course create(Course newCourse) throws InvalidInputException {
        validateCourse(newCourse);
        return courseRepository.create(newCourse);
    }

    @Override
    public Course findById(int id) {
        log.info("Course request was sent to service with id: {}", id);
        Course courseFound = courseRepository.findById(id);
        log.info("Course as found: {}", courseFound);
        return courseFound;
    }

    public boolean update(Course course) throws InvalidInputException {
        validateCourse(course);
        return courseRepository.update(course);
    }

    public boolean delete(int id) {
        return courseRepository.delete(id);
    }

    public List<Course> findAvailable() {
        List<Course> courses = courseRepository.findAvailable();
        log.info("Searching for available courses");
        if (courses.isEmpty()) throw new DataNotFoundException("No Courses Found.");
        else return courses;
    }

    public List<Course> findEnrolled(int curUser) {
        List<Course> courses = courseRepository.getEnrolled(curUser);
        log.info("searching for user: {}'s enrolled courses", curUser);
        if (courses.isEmpty()) throw new DataNotFoundException("No Courses Found.");
        else return courses;
    }

    private void validateCourse(Course course) throws InvalidInputException {
        log.info("validating course: {}", course);
        if (course == null) throw new InvalidInputException("course is null, has not been instantiated");
        String regex = "[A-Z]{4}\\d{3}-\\d";
        //              [A-Z]{4}    checks for 4 capital letter, as subject/department
        //              \\d{3}      checks for 3 digits, as a course number
        //              -\\d{2}     checks for 1 digit following "-", as a section number
        //              Example:    MATH101-1 for Math 101, Section 1
        if (!course.getCourseCode().matches(regex)) {
            log.warn("malformed course code: {}", course.getCourseCode());
            throw new InvalidInputException("Malformed course code");
        }
        //TODO: other criteria
    }
}
