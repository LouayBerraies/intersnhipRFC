package com.example.finale.service.jwt;

import com.example.finale.entities.Formation;
import com.example.finale.entities.Restaurant;
import com.example.finale.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository repository;


    @Override
    public Long saveRestaurant(Restaurant restaurant) {
        return repository.save(restaurant).getIdRestaurant();
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return (List<Restaurant>) repository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteRestaurant(Integer sno) {
        repository.deleteById(sno);
    }

    @Override
    public List<Object[]> getCommandCountsForRestaurants() {
        return repository.getCommandCountsForRestaurants();
    }

}
