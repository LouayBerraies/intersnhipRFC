package com.example.finale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
public class ReservationTrajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idReservation;

    private int nbPassager;
    private Date dateReservation;
    private Long prix;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne
    @JoinColumn(name = "iduser") // Spécification de la colonne
    private User user;

    @ManyToOne
    private Trajet trajet;




}
