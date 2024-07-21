package com.example.finale.controller;

import com.example.finale.entities.Colocataire;
import com.example.finale.entities.Formation;
import com.example.finale.entities.Logement;
import com.example.finale.entities.Status;
import com.example.finale.repository.ColocataireRepository;
import com.example.finale.repository.LogementRepository;
import com.example.finale.service.jwt.FormationService;
import com.example.finale.service.jwt.LogementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/logementDetails")
public class LogementController {

    @Autowired
    private LogementService service;
    private LogementRepository logementRepository;
    private ColocataireRepository colocataireRepository;
    @PostMapping("/saveLogement")
    public ResponseEntity<String> saveLogement( @RequestBody Logement logement){
        Long id = service.saveLogement(logement);
        return  new ResponseEntity<String>("Logement with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/logementList")
    public ResponseEntity<List<Logement>> getAllLogementDetails(){
        List<Logement> list = service.getAllLogement();
        return new ResponseEntity<List<Logement>>(list,HttpStatus.OK);
    }

    @GetMapping("/getLogementById/{sno}")
    public ResponseEntity<Logement> getLogementById( @PathVariable("sno")  Integer sno){
        Logement std = service.getLogementById(sno);
        return new ResponseEntity<Logement>(std,HttpStatus.OK);
    }
    @PutMapping("/updateLogement/{sno}")
    public ResponseEntity<String> updateLogement( @PathVariable("sno") int sno,  @RequestBody Logement logement){
        Logement stdFromDb = service.getLogementById(sno);
        stdFromDb.setPhoneNum(logement.getPhoneNum());
        stdFromDb.setAdresse(logement.getAdresse());
        stdFromDb.setEmail(logement.getEmail());
        stdFromDb.setDescription(logement.getDescription());
        stdFromDb.setMonthlyRent(logement.getMonthlyRent());
        stdFromDb.setRoomNumber(logement.getRoomNumber());
        stdFromDb.setBedNumber(logement.getBedNumber());
        stdFromDb.setStatus(logement.getStatus());
        stdFromDb.setImage(logement.getImage());


        Long id = service.saveLogement(stdFromDb);
        return new ResponseEntity<String>("Formation with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteLogement/{sno}")
    public ResponseEntity<String> deleteFormation(@PathVariable("sno") Integer sno){
        service.deleteLogement(sno);
        return new ResponseEntity<String>("Formation with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @GetMapping("/allLogementIds")
    public ResponseEntity<List<Long>> getAllLogementIds() {
        List<Long> logementIds = logementRepository.findAllIds(); // Adjust this line according to your LogementRepository
        return new ResponseEntity<List<Long>>(logementIds, HttpStatus.OK);
    }
    @PostMapping("/logements/{logementId}/colocataires")
    public ResponseEntity<String> addColocataireToLogement(@PathVariable Long idLogement, @RequestBody Colocataire colocataire) {
        Logement logement = logementRepository.findById(Math.toIntExact(idLogement))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logement not found with id: " + idLogement));

        if (logement.getBedNumber() > 0) {
            colocataire.setLogement(logement);
            colocataireRepository.save(colocataire);

            // Decrease bedCount by 1
            logement.setBedNumber(logement.getBedNumber() - 1);
            logementRepository.save(logement);

            return ResponseEntity.ok("Colocataire added successfully to logement.");
        } else {
            return ResponseEntity.badRequest().body("No beds available for this logement.");
        }
    }
    @PutMapping("/{id}/recommend")
    public ResponseEntity<?> recommendLogement(@PathVariable Integer id) {
        try {
            service.recommendLogement(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to recommend logement");
        }
    }

    @PutMapping("/{id}/unrecommend")
    public ResponseEntity<?> unrecommendLogement(@PathVariable Integer id) {
        try {
            service.unrecommendLogement(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unrecommend logement");
        }
    }

    @GetMapping("/recommendation")
    public ResponseEntity<List<Logement>> getAllLogementsSortedByRecommendation() {
        List<Logement> logements = service.getAllLogementsSortedByRecommendation();
        return ResponseEntity.ok().body(logements);
    }

    @GetMapping("/address/{adresse}")
    public ResponseEntity<List<Logement>> getLogementsByAddress(@PathVariable String adresse) {
        List<Logement> logements = service.findByAdresseSortedByRecommendation(adresse);
        return ResponseEntity.ok().body(logements);
    }
}


