package com.example.finale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Trajet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idTrajet;

    private String lieuDepart;
    private String lieuArrivee;
    private Date dateDepart;
    private Long placesDispo;
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
    @JsonIgnore
   @OneToMany(mappedBy = "trajet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservationTrajet> reservations;

}
