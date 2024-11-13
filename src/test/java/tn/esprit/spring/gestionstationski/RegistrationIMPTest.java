package tn.esprit.spring.gestionstationski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.gestionstationski.entities.*;
import tn.esprit.spring.gestionstationski.repositories.ICourseRepository;
import tn.esprit.spring.gestionstationski.repositories.IRegistrationRepository;
import tn.esprit.spring.gestionstationski.repositories.ISkierRepository;
import tn.esprit.spring.gestionstationski.services.RegistrationServicesImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class RegistrationIMPTest {



        @Mock
        private IRegistrationRepository registrationRepository;

        @Mock
        private ISkierRepository skierRepository;

        @Mock
        private ICourseRepository courseRepository;

        @InjectMocks
        private RegistrationServicesImpl registrationServices;

        private Skier skier;
        private Course course;
        private Registration registration;

        @BeforeEach
        void setUp() {
            // Initialize the objects used in the tests
            skier = new Skier();
            skier.setNumSkier(1L);
            skier.setDateOfBirth(LocalDate.of(2000, 1, 1));

            course = new Course();
            course.setNumCourse(1L);
            course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);

            registration = new Registration();
            registration.setNumWeek(1);
        }

        @Test
        void testAddRegistrationAndAssignToSkier() {
            // Arrange: Mocking repository interactions
            when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
            when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

            // Act: Calling the service method
            Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 1L);

            // Assert: Verifying the expected outcome
            assertNotNull(result);
            assertEquals(skier, result.getSkier());
            verify(registrationRepository, times(1)).save(any(Registration.class));
        }

        @Test
        void testAssignRegistrationToCourse() {
            // Arrange: Mocking repository interactions
            when(registrationRepository.findById(1L)).thenReturn(Optional.of(registration));
            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

            // Act: Calling the service method
            Registration result = registrationServices.assignRegistrationToCourse(1L, 1L);

            // Assert: Verifying the expected outcome
            assertNotNull(result);
            assertEquals(course, result.getCourse());
            verify(registrationRepository, times(1)).save(any(Registration.class));
        }

        @Test
        void testAddRegistrationAndAssignToSkierAndCourse() {
            // Arrange: Mocking repository interactions
            when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(), anyLong(), anyLong())).thenReturn(0L);
            when(registrationRepository.countByCourseAndNumWeek(any(Course.class), anyInt())).thenReturn(5L);
            when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

            // Act: Calling the service method
            Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);

            // Assert: Verifying the expected outcome
            assertNotNull(result);
            assertEquals(skier, result.getSkier());
            assertEquals(course, result.getCourse());
            verify(registrationRepository, times(1)).save(any(Registration.class));
        }

        @Test
        void testAddRegistrationAndAssignToSkierAndCourse_FullCourse() {
            // Arrange: Mocking repository interactions
            when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));
            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            when(registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(anyInt(), anyLong(), anyLong())).thenReturn(0L);
            when(registrationRepository.countByCourseAndNumWeek(any(Course.class), anyInt())).thenReturn(6L); // Course is full

            // Act: Calling the service method
            Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);

            // Assert: Verifying the expected outcome
            assertNull(result); // The registration should return null because the course is full
            verify(registrationRepository, times(0)).save(any(Registration.class)); // save should not be called
        }

        @Test
        void testAddRegistrationAndAssignToSkierAndCourse_SkierNotFound() {
            // Arrange: Mocking repository interactions
            when(skierRepository.findById(1L)).thenReturn(Optional.empty());
            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

            // Act: Calling the service method
            Registration result = registrationServices.addRegistrationAndAssignToSkierAndCourse(registration, 1L, 1L);

            // Assert: Verifying the expected outcome
            assertNull(result); // The registration should return null because the skier does not exist
        }
    }

