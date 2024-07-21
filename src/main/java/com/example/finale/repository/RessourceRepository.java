package com.example.finale.repository;

import com.example.finale.entities.Commande;
import com.example.finale.entities.Ressource;
import com.example.finale.entities.RessourceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RessourceRepository extends JpaRepository<Ressource,Integer> {
    List<Ressource> findByStatus(RessourceStatus status);
    Long countByStatus (RessourceStatus status);

}
