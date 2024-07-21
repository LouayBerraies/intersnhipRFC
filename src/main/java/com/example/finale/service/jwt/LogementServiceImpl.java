package com.example.finale.service.jwt;

import com.example.finale.entities.Logement;
import com.example.finale.entities.Restaurant;
import com.example.finale.repository.LogementRepository;
import com.example.finale.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogementServiceImpl implements LogementService{
    @Autowired
    private LogementRepository repository;


    @Override
    public Long saveLogement(Logement logement) {
        return repository.save(logement).getIdLogement();
    }

    @Override
    public List<Logement> getAllLogement() {
        return (List<Logement>) repository.findAll();
    }

    @Override
    public Logement getLogementById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteLogement(Integer sno) {
        repository.deleteById(sno);
    }
    public void recommendLogement(Integer logementId) {
        Logement logement = repository.findById(logementId).orElse(null);
        if (logement != null) {
            logement.incrementRecommendation();
            repository.save(logement);
        }
    }

    public void unrecommendLogement(Integer logementId) {
        Logement logement = repository.findById(logementId).orElse(null);
        if (logement != null) {
            logement.decrementRecommendation();
            repository.save(logement);
        }
    }
    @Override
    public List<Logement> getAllLogementsSortedByRecommendation() {
        // Implement sorting logic using repository method
        return repository.findAllByOrderByRecommendationDesc();
    }

    @Override
    public List<Logement> findByAdresseSortedByRecommendation(String adresse) {
        // Implement filtering logic using repository method
        return repository.findByAdresseOrderByRecommendationDesc(adresse);
    }
}
