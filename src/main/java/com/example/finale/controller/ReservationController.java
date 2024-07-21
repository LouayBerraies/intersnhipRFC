package com.example.finale.controller;

import com.example.finale.entities.Reservation;
import com.example.finale.service.jwt.ReservationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/reservationDetails")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("/saveReservation")
    public ResponseEntity<String> saveFormation(@RequestBody Reservation reservation){
        Long id = service.saveReservation(reservation);
        return  new ResponseEntity<String>("Reservation with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/reservationList")
    public ResponseEntity<List<Reservation>> getAllReservationDetails(){
        List<Reservation> list = service.getAllReservations();
        return new ResponseEntity<List<Reservation>>(list,HttpStatus.OK);
    }

    @GetMapping("/getReservationById/{sno}")
    public ResponseEntity<Reservation> getReservationById( @PathVariable("sno")  Integer sno){
        Reservation std = service.getReservationById(sno);
        return new ResponseEntity<Reservation>(std,HttpStatus.OK);
    }
    @PutMapping("/updateReservation/{sno}")
    public ResponseEntity<String> updateReservation( @PathVariable("sno") Integer sno,  @RequestBody Reservation reservation){

        Reservation stdFromDb = service.getReservationById(sno);

        stdFromDb.setDateDebut(reservation.getDateDebut());
        stdFromDb.setDateFin(reservation.getDateFin());
        stdFromDb.setTypeReservation(reservation.getTypeReservation());
        stdFromDb.setStatus(reservation.getStatus());



        Long id = service.saveReservation(stdFromDb);
        return new ResponseEntity<String>("Reservation with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteReservation/{sno}")
    public ResponseEntity<String> deleteReservation(@PathVariable("sno") Integer sno){
        service.deleteReservation(sno);
        return new ResponseEntity<String>("Reservation with '"+sno+"' has been deleted",HttpStatus.OK);
    }
    @PutMapping("/addReservationAndAffect/{id}/{sno}")
    public Reservation addReservationAndAffect(@RequestBody Reservation reservation,@PathVariable Integer id,@PathVariable Long sno ) {
        return service.addReservationAndAffect(reservation,id,sno);
    }

    @GetMapping("/reservationsEn_Attente")
    public Integer reservationsEn_Attente( ) {
        return service.reservationsEnAttente();
    }
    @PutMapping("/refuserReservation/{id}")
    public Reservation refuserReservation(@PathVariable Integer id) {
        return service.refuserReservation(id);
    }
    @GetMapping("/reservationsTotal")
    public Integer reservationsTotal( ) {
        return service.totalReservations();
    }

    @PutMapping("/acceptReservation/{id}")
    public Reservation acceptReservation(@PathVariable Integer id) throws MessagingException {
        return service.acceptReservation(id);
    }



}

