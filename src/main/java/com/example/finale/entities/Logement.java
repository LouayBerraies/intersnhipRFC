package com.example.finale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLogement;
    private Long phoneNum;
    private String adresse;
    private String email;
    private String description;
    private Float monthlyRent;
    private Long roomNumber;
    private Long bedNumber;
    private String lat;
    private String lng;
    private int recommendation;

    @ElementCollection // Use ElementCollection to store a collection of basic types
    private Set<String>  image;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "logement")
    private Set<Colocataire> colocataires;


    public void incrementRecommendation() {
        this.recommendation++;
    }

    public void decrementRecommendation() {
        if (this.recommendation > 0) {
            this.recommendation--;
        }
    }
}
