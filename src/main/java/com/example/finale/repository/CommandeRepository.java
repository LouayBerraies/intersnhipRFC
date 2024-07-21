package com.example.finale.repository;

import com.example.finale.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
}
