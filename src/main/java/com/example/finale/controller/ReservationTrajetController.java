package com.example.finale.controller;

import com.example.finale.entities.Notification;
import com.example.finale.entities.ReservationTrajet;
import com.example.finale.entities.Trajet;
import com.example.finale.service.jwt.NotificationService;
import com.example.finale.service.jwt.ReservationTrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservationTrajetDetails")
public class ReservationTrajetController {

    @Autowired
    private ReservationTrajetService service;
    @Autowired

    private NotificationService servicenotif;
    @PostMapping("/saveReservationTrajet")
    public ResponseEntity<String> saveReservationTrajet( @RequestBody ReservationTrajet reservationTrajet){
        Long id = service.saveReservationTrajet(reservationTrajet);
        return  new ResponseEntity<String>("ReservationTrajet with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/reservationTrajetList")
    public ResponseEntity<List<ReservationTrajet>> getAllReservationTrajetDetails(){
        List<ReservationTrajet> list = service.getAllReservationTrajets();
        return new ResponseEntity<List<ReservationTrajet>>(list,HttpStatus.OK);
    }

    @GetMapping("/getReservationTrajetById/{sno}")
    public ResponseEntity<ReservationTrajet> getReservationTrajetById( @PathVariable("sno")  Integer sno){
        ReservationTrajet std = service.getReservationTrajetById(sno);
        return new ResponseEntity<ReservationTrajet>(std,HttpStatus.OK);
    }
    @PutMapping("/updateReservationTrajet/{sno}")
    public ResponseEntity<String> updateReservationTrajet( @PathVariable("sno") Integer sno,  @RequestBody ReservationTrajet reservationTrajet){
        ReservationTrajet stdFromDb = service.getReservationTrajetById(sno);
        stdFromDb.setNbPassager(reservationTrajet.getNbPassager());
        stdFromDb.setDateReservation(reservationTrajet.getDateReservation());
        stdFromDb.setPrix(reservationTrajet.getPrix());
        stdFromDb.setStatus(reservationTrajet.getStatus());

        Long id = service.saveReservationTrajet(stdFromDb);
        return new ResponseEntity<String>("Formation with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteReservationTrajet/{sno}")
    public ResponseEntity<String> deleteReservationTrajet(@PathVariable("sno") Integer sno){
        service.deleteReservationTrajet(sno);
        return new ResponseEntity<String>("ReservationTrajet with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @GetMapping("/findbytrajet/{idTrajet}")
    public List<ReservationTrajet> getReservationsByTrajetId(@PathVariable Integer idTrajet) {
        return service.GetReservationsByTrajetId(idTrajet);
    }
    @GetMapping("/reservationcount")
    public int getReservationCount() {
        return service.getsumreservations();
    }

    @GetMapping("/allHistory")
    public List<Notification> afficherhistorique() {
        return service.getAllNotif();
    }


    @GetMapping("/allHistory/{UserId}")
    public List<Notification> afficherhistorique(@PathVariable("UserId") long UserId) {
        return service.getNotifByUser(UserId);
    }

}

