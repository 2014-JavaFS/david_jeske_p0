package com.revature.crs.Course;

import com.revature.crs.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class CourseServiceTestSuite {

    @Mock
    private CourseRepository mockCourseRepository;
    @InjectMocks
    private CourseService sut;

    @BeforeEach
    public void setUp(){
        mockCourseRepository = mock(CourseRepository.class);
        sut = new CourseService(mockCourseRepository);
    }

    @Test
    public void testCreate_ValidData() throws InvalidInputException {
        Course validCourse = new Course(9, "MATH370-4", "Advanced Calculus", (short) 20);
        when(mockCourseRepository.create(validCourse)).thenReturn(validCourse);

        Course returnedCourse = sut.create(validCourse);

        // Assert - making sure the action returns as excepted
        assertEquals(validCourse, returnedCourse);
        verify(mockCourseRepository, times(1)).create(validCourse);
    }

    @Test
    public void testFindAll_NotEmptyArray() throws InvalidInputException{
        Course course = new Course();

        sut.create(course);
        List<Course> retrievedCourses = sut.findAll();

        assertFalse(retrievedCourses.isEmpty());
    }
}
