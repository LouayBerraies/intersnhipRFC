package com.example.finale.repository;

import com.example.finale.entities.Formation;
import com.example.finale.entities.ReservationTrajet;
import com.example.finale.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrajetRepository extends JpaRepository<Trajet,Integer> {
    List<Trajet> findByLieuDepartAndAndLieuArrivee(String dep, String arriv);
    List<Trajet> findByLieuDepart (String dep);
}
