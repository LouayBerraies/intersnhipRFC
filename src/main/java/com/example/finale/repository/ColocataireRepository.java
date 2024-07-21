package com.example.finale.repository;

import com.example.finale.entities.Colocataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColocataireRepository extends JpaRepository<Colocataire,Integer> {
}
