package com.example.finale.controller;

import com.example.finale.entities.Colocataire;
import com.example.finale.entities.Logement;
import com.example.finale.entities.Reservation;
import com.example.finale.repository.LogementRepository;
import com.example.finale.service.jwt.ColocataireService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/colocataireDetails")
public class ColocataireController {

    @Autowired
    private ColocataireService service;
    private LogementRepository logementrepo;

    @PostMapping("/saveColocataire")
    public ResponseEntity<String> saveFormation(@RequestBody Colocataire colocataire){
        Logement logement = colocataire.getLogement();
        colocataire.setLogement(logement);

        Long id = service.saveColocataire(colocataire);
        return  new ResponseEntity<String>("Colocataire with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/colocataireList")
    public ResponseEntity<List<Colocataire>> getAllColocataireDetails(){
        List<Colocataire> list = service.getAllColocataire();
        return new ResponseEntity<List<Colocataire>>(list,HttpStatus.OK);
    }

    @GetMapping("/getColocataireById/{sno}")
    public ResponseEntity<Colocataire> getColocataireById( @PathVariable("sno")  Integer sno){
        Colocataire std = service.getColocataireById(sno);
        return new ResponseEntity<Colocataire>(std,HttpStatus.OK);
    }
    @PutMapping("/updateColocataire/{sno}")
    public ResponseEntity<String> updateCColocataire( @PathVariable("sno") Integer sno,  @RequestBody Colocataire colocataire){

        Colocataire stdFromDb = service.getColocataireById(sno);

        stdFromDb.setName(colocataire.getName());
        stdFromDb.setInDate(colocataire.getInDate());
        stdFromDb.setOutDate(colocataire.getOutDate());
        stdFromDb.setRes(colocataire.getRes());



        Long id = service.saveColocataire(stdFromDb);
        return new ResponseEntity<String>("Colocataire with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteColocataire/{sno}")
    public ResponseEntity<String> deleteColocataire(@PathVariable("sno") Integer sno){
        service.deleteColocataire(sno);
        return new ResponseEntity<String>("Colocataire with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @PutMapping("/acceptColocataire/{id}")
    public Colocataire acceptReservation(@PathVariable Integer id) throws MessagingException {
        return service.acceptColocataire(id);
    }
    @PutMapping("/refuserReservation/{id}")
    public Colocataire refuserColocataire(@PathVariable Integer id) {
        return service.refuserColocataire(id);
    }

}
