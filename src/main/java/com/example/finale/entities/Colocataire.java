package com.example.finale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter

public class Colocataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColocataire;
    private Date inDate;
    private Date outDate;
    private String name;
    private String res;
    @ManyToOne
    private Logement logement;


}
