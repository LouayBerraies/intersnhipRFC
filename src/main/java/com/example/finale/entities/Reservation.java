package com.example.finale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    private Date dateDebut;
    private Date dateFin;
    @Enumerated(EnumType.STRING)

    private TypeReservation typeReservation;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @ManyToOne
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Ressource ressource;
}
