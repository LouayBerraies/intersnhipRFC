package com.example.finale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idRessource;

    private String label;

    @Enumerated(EnumType.STRING)
    private RessourceStatus status;

    @Enumerated(EnumType.STRING)
    private TypeRessource typeRessource;

    private Integer capacite;
    private Integer nbR;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ressource", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;


    private String image;
}
