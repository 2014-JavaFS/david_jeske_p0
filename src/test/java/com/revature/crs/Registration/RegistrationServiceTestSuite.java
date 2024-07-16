package com.revature.crs.Registration;

import com.revature.crs.util.exceptions.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegistrationServiceTestSuite {
    //@Mock // indicates the object needing to be mocked
    private RegistrationRepository mockRegistrationRepository;

    //@InjectMocks // inject our RegistrationRepository into our RegistrationService as a mocked objected
    private RegistrationService sut;

    @BeforeEach
    public void setUp() {
        mockRegistrationRepository = mock(RegistrationRepository.class);
        sut = new RegistrationService(mockRegistrationRepository);
    }

    @Test
    public void testCreate_ValidCourse() throws InvalidInputException {
        // AAA
        // Arrange - what do you need? Any objects created for testing purposes?
        Registration validRegistration = new Registration(999, 1, 5, LocalDate.of(2024, 7, 16));
        when(mockRegistrationRepository.create(validRegistration)).thenReturn(validRegistration);

        // Action - the method invocation
        Registration returnedRegistration = sut.create(validRegistration);

        // Assert - making sure the action returns as excepted
        assertEquals(validRegistration, returnedRegistration);
        verify(mockRegistrationRepository, times(1)).create(validRegistration);
    }

    // TDD - Test Driven Development
    /*
        Write your tests prior to implementation!
        100% Test Coverage
        You write tests before you ever write ANY implementation
     */

    @Test
    public void testCreate_InvalidRegistration_Null() {
        InvalidInputException thrown = assertThrows(InvalidInputException.class, () -> {
            sut.create(null);
        }, "User is null and not instantiated");

        assertEquals(InvalidInputException.class, thrown.getClass());
    }

    @Test
    public void testCreate_InvalidRegistration_RegistrationId() {
        Registration invalidRegistrationId = new Registration(-1, 1, 5, LocalDate.of(2024, 7, 16));

        Assertions.assertThrows(InvalidInputException.class, () -> sut.create(invalidRegistrationId));
    }

    @Test
    public void testCreate_InvalidRegistration_CourseId() {
        Registration invalidCourseId = new Registration(999, -9, 5, LocalDate.of(2024, 7, 16));

        assertThrows(InvalidInputException.class, () -> sut.create(invalidCourseId));
    }

    @Test
    public void testCreate_InvalidRegistration_StudentId() {
        Registration invalidStudentId = new Registration(999, 1, -5, LocalDate.of(2024, 7, 16));

        assertThrows(InvalidInputException.class, () -> sut.create(invalidStudentId));
    }

    @Test
    public void testCreate_InvalidRegistration_RegistrationDate() {
        Registration invalidRegistrationDate = new Registration(999, 1, 5, LocalDate.of(1924, 7, 16));

        assertThrows(InvalidInputException.class, () -> sut.create(invalidRegistrationDate));
    }

    @Test
    public void testUpdate_ValidRegistration() throws InvalidInputException {
        // AAA
        Registration validRegistration = new Registration(999, 1, 5, LocalDate.of(2024, 7, 16));
        when(mockRegistrationRepository.update(validRegistration)).thenReturn(true);

        boolean returnedUpdate = sut.update(validRegistration);
        assertTrue(returnedUpdate);
        verify(mockRegistrationRepository, times(1)).update(validRegistration);
    }
}
