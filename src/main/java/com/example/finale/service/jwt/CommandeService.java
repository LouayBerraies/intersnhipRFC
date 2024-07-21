package com.example.finale.service.jwt;

import com.example.finale.entities.Commande;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CommandeService {
    public Long saveCommande(Commande commande);

    public List<Commande> getAllCommandes();
    public Commande getCommandeById(Integer id);

    public void deleteCommande(Integer id);

    @Transactional
    Commande addCommandeAndAffect(Commande commande, Integer restaurantId,Long userId);

    int getCommandeCountForToday();

    int getCommandeCountForCurrentWeek();

    Commande rateOrder(Integer orderId, Integer rating);

    String getRestaurantNameForCommande(Integer id);
}
