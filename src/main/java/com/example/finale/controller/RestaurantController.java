package com.example.finale.controller;

import com.example.finale.entities.Restaurant;
import com.example.finale.service.jwt.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantDetails")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @PostMapping("/saveRestaurant")
    public ResponseEntity<String> saveFormation(@RequestBody Restaurant restaurant){
        Long id = service.saveRestaurant(restaurant);
        return  new ResponseEntity<String>("Restaurant with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/restaurantList")
    public ResponseEntity<List<Restaurant>> getAllRestaurantDetails(){
        List<Restaurant> list = service.getAllRestaurants();
        return new ResponseEntity<List<Restaurant>>(list,HttpStatus.OK);
    }

    @GetMapping("/getRestaurantById/{sno}")
    public ResponseEntity<Restaurant> getRestaurantById( @PathVariable("sno")  Integer sno){
        Restaurant std = service.getRestaurantById(sno);
        return new ResponseEntity<Restaurant>(std,HttpStatus.OK);
    }
    @PutMapping("/updateRestaurant/{sno}")
    public ResponseEntity<String> updateRestaurant( @PathVariable("sno") Integer sno,  @RequestBody Restaurant restaurant){

        Restaurant stdFromDb = service.getRestaurantById(sno);
        stdFromDb.setMenu(restaurant.getMenu());
        stdFromDb.setMenuType(restaurant.getMenuType());
        Long id = service.saveRestaurant(stdFromDb);
        return new ResponseEntity<String>("Restaurant with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteRestaurant/{sno}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable("sno") Integer sno){
        service.deleteRestaurant(sno);
        return new ResponseEntity<String>("Restaurant with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @GetMapping("/command-counts")
    public List<Object[]> getCommandCountsForRestaurants() {
        return service.getCommandCountsForRestaurants();
    }
}
