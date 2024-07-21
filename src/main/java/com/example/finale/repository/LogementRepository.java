package com.example.finale.repository;

import com.example.finale.entities.Logement;
import com.example.finale.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogementRepository extends JpaRepository<Logement,Integer> {
    Logement findByIdLogement(long id);
    @Query("SELECT l.idLogement FROM Logement l")
    List<Long> findAllIds();
    List<Logement> findAllByOrderByRecommendationDesc();
    List<Logement> findByAdresseOrderByRecommendationDesc(String adresse); }
