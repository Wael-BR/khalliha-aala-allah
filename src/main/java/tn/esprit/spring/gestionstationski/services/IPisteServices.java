package tn.esprit.spring.gestionstationski.services;
import tn.esprit.spring.gestionstationski.entities.Piste;

import java.util.List;

public interface IPisteServices {

    List<Piste> retrieveAllPistes();

    Piste  addPiste(Piste  piste);

    void removePiste (Long numPiste);

    Piste retrievePiste (Long numPiste);
}
