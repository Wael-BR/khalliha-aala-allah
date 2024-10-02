package tn.esprit.spring.gestionstationski.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.gestionstationski.entities.Piste;

public interface IPisteRepository extends JpaRepository<Piste, Long> {

}
