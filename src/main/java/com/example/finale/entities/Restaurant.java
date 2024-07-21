package com.example.finale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;
    private String menu;
    private String imageUrl;
    private Float price;

    @Enumerated(EnumType.STRING)

    private MenuType menuType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Commande> commandes;

    public String getName() {
        return this.menu;
    }
}
