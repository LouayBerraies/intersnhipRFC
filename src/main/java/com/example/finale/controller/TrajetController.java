package com.example.finale.controller;

import com.example.finale.entities.Trajet;
import com.example.finale.service.jwt.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trajetDetails")
public class TrajetController {

    @Autowired
    private TrajetService service;

    @PostMapping("/saveTrajet")
    public ResponseEntity<String> saveFormation(@RequestBody Trajet trajet){
        Long id = service.saveTrajet(trajet);
        return  new ResponseEntity<String>("Trajet with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/trajetList")
    public ResponseEntity<List<Trajet>> getAllTrajetDetails(){
        List<Trajet> list = service.getAllTrajets();
        return new ResponseEntity<List<Trajet>>(list,HttpStatus.OK);
    }

    @GetMapping("/getTrajetById/{sno}")
    public ResponseEntity<Trajet> getTrajetById( @PathVariable("sno")  Integer sno){
        Trajet std = service.getTrajetById(sno);
        return new ResponseEntity<Trajet>(std,HttpStatus.OK);
    }
    @PutMapping("/updateTrajet/{sno}")
    public ResponseEntity<String> updateTrajet( @PathVariable("sno") Integer sno,  @RequestBody Trajet trajet){

        Trajet stdFromDb = service.getTrajetById(sno);

        stdFromDb.setLieuDepart(trajet.getLieuDepart());
        stdFromDb.setLieuArrivee(trajet.getLieuArrivee());
        stdFromDb.setDateDepart(trajet.getDateDepart());
        stdFromDb.setPlacesDispo(trajet.getPlacesDispo());



        Long id = service.saveTrajet(stdFromDb);
        return new ResponseEntity<String>("Trajet with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteTrajet/{sno}")
    public ResponseEntity<String> deleteTrajet(@PathVariable("sno") Integer sno){
        service.deleteTrajet(sno);
        return new ResponseEntity<String>("Trajet with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @GetMapping("/totalAvailableSeats")
    public ResponseEntity<Integer> getTotalAvailableSeats() {
        int totalAvailableSeats = service.getTotalAvailableSeats();
        return new ResponseEntity<>(totalAvailableSeats, HttpStatus.OK);
    }
    @GetMapping("/search-trajet/{dep}/{arriv}")
    public ResponseEntity<List<Trajet>> searchTrajet(@PathVariable("dep") String departure, @PathVariable("arriv") String arrival) {
        List<Trajet> trajets = service.RechercheTrajet(departure, arrival);
        return ResponseEntity.ok(trajets);
    }
    @GetMapping("/search-trajet/{dep}")
    public ResponseEntity<List<Trajet>> searchTrajet(@PathVariable("dep") String departure) {
        List<Trajet> trajets = service.RechercheTrajetDep(departure);
        return ResponseEntity.ok(trajets);
    }
}
