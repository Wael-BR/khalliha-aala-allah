package tn.esprit.spring.gestionstationski.services;
import tn.esprit.spring.gestionstationski.entities.Course;
import tn.esprit.spring.gestionstationski.entities.TypeCourse;

import java.util.List;

public interface ICourseServices {

    List<Course> retrieveAllCourses();

    Course  addCourse(Course  course);

    Course updateCourse(Course course);

    Course retrieveCourse(Long numCourse);


}
