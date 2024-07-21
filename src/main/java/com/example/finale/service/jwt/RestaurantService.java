package com.example.finale.service.jwt;

import com.example.finale.entities.Formation;
import com.example.finale.entities.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Long saveRestaurant(Restaurant restaurant);

    public List<Restaurant> getAllRestaurants();
    public Restaurant getRestaurantById(Integer id);

    public void deleteRestaurant(Integer id);
    List<Object[]> getCommandCountsForRestaurants();
}
