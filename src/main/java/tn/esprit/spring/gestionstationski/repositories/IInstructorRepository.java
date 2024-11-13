package tn.esprit.spring.gestionstationski.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.gestionstationski.entities.Instructor;



public interface IInstructorRepository extends JpaRepository<Instructor, Long> {

}
