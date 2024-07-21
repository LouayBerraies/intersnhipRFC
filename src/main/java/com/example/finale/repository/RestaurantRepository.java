package com.example.finale.repository;

import com.example.finale.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    @Query("SELECT r.idRestaurant, r.menu, COUNT(c) FROM Restaurant r LEFT JOIN r.commandes c GROUP BY r.idRestaurant, r.menu")
    List<Object[]> getCommandCountsForRestaurants();
}
