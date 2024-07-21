package com.example.finale.service.jwt;

import com.example.finale.entities.Logement;
import com.example.finale.entities.Restaurant;

import java.util.List;

public interface LogementService {
    public Long saveLogement(Logement logement);

    public List<Logement> getAllLogement();
    public Logement getLogementById(Integer id);

    public void deleteLogement(Integer id);
    public void recommendLogement(Integer logementId);
    public void unrecommendLogement(Integer logementId);
    List<Logement> getAllLogementsSortedByRecommendation();
    List<Logement> findByAdresseSortedByRecommendation(String adresse);

}
