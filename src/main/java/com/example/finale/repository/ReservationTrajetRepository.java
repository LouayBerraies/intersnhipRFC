package com.example.finale.repository;

import com.example.finale.entities.ReservationTrajet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationTrajetRepository extends CrudRepository<ReservationTrajet,Integer> {
    List<ReservationTrajet> findByTrajetIdTrajet(Integer trajetId);
    List<ReservationTrajet> findAll() ;



}
