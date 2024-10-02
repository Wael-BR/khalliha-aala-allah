package tn.esprit.spring.gestionstationski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.gestionstationski.entities.Course;
import tn.esprit.spring.gestionstationski.entities.TypeCourse;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, Long> {



}
