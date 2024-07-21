package com.example.finale.controller;

import com.example.finale.entities.Commande;
import com.example.finale.service.jwt.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/commandeDetails")
public class CommandeController {

    @Autowired
    private CommandeService service;

    @PostMapping("/saveCommande")
    public ResponseEntity<String> saveFormation(@RequestBody Commande commande){
        Long id = service.saveCommande(commande);
        return  new ResponseEntity<String>("Commande with '"+id+"' has been saved", HttpStatus.OK);

    }
    /*
    @PostMapping("/saveCommande/{userId}")
    public ResponseEntity<String> saveFormation(@PathVariable("userId") Long userId, @RequestBody Commande commande){
        Integer id = service.saveCommande(userId, commande).intValue(); // Pass userId to the service method
        return new ResponseEntity<String>("Commande with '" + id + "' has been saved", HttpStatus.OK);
    }*/

    @GetMapping("/commandeList")
    public ResponseEntity<List<Commande>> getAllCommandeDetails(){
        List<Commande> list = service.getAllCommandes();
        return new ResponseEntity<List<Commande>>(list,HttpStatus.OK);
    }

    @GetMapping("/getCommandeById/{sno}")
    public ResponseEntity<Commande> getCommandeById( @PathVariable("sno")  Integer sno){
        Commande std = service.getCommandeById(sno);
        return new ResponseEntity<Commande>(std,HttpStatus.OK);
    }
/*
    @PutMapping("/updateCommande/{sno}")
    public ResponseEntity<String> updateCommande( @PathVariable("sno") Integer sno,  @RequestBody Commande commande){

        Commande stdFromDb = service.getCommandeById(sno);

        stdFromDb.setQuantite(commande.getQuantite());
        stdFromDb.setMontant(commande.getMontant());
        stdFromDb.setDateCommande(commande.getDateCommande());

        Long id = service.saveCommande(stdFromDb);
        return new ResponseEntity<String>("Commande with '"+id+"' has been updated",HttpStatus.OK);

    }*/
    @DeleteMapping("deleteCommande/{sno}")
    public ResponseEntity<String> deleteCommande(@PathVariable("sno") Integer sno){
        service.deleteCommande(sno);
        return new ResponseEntity<String>("Commande with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @PostMapping ("/addCommandeAndAffect/{restaurantId}/{sno}")
    public ResponseEntity<Commande> addCommandeAndAffect(@RequestBody Commande commande, @PathVariable Integer restaurantId,@PathVariable Long sno) {
        Commande newCommande = service.addCommandeAndAffect(commande, restaurantId,sno);
        if (newCommande != null) {
            return new ResponseEntity<>(newCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/countToday")
    public int getCountOfCommandesForToday() {
        return service.getCommandeCountForToday();
    }

    @GetMapping("/countWeek")
    public int getCountOfCommandesForCurrentWeek() {
        return service.getCommandeCountForCurrentWeek();
    }


    @GetMapping("/{id}/")
    public ResponseEntity<String> getRestaurantNameForCommande(@PathVariable Integer id) {
        String restaurantName = service.getRestaurantNameForCommande(id);
        if (restaurantName != null) {
            return new ResponseEntity<>(restaurantName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}/rate/{rating}")
    public ResponseEntity<Commande> rateOrder(@PathVariable Integer id, @PathVariable Integer rating) {
        Commande ratedCommande = service.rateOrder(id, rating);
        if (ratedCommande != null) {
            return new ResponseEntity<>(ratedCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
