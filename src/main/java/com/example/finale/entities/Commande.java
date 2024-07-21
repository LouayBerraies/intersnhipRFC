package com.example.finale.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    private Long quantite;
    private Float montant ;
    private Date dateCommande;
    private Integer rating;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurantId") // Ensure proper join column
    private Restaurant restaurant;


    public void setUserId(Integer userId) {
        this.user = user;

    }
}
