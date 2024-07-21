package com.example.finale.service.jwt;

import com.example.finale.entities.Restaurant;
import com.example.finale.entities.Trajet;

import java.util.List;

public interface TrajetService {

    public Long saveTrajet(Trajet trajet);

    public List<Trajet> getAllTrajets();
    public Trajet getTrajetById(Integer id);

    public void deleteTrajet(Integer id);
    int getTotalAvailableSeats();
    public List<Trajet> RechercheTrajet (String dep,String Arriv);
    public List<Trajet> RechercheTrajetDep (String dep);
}
