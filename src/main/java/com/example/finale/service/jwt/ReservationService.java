package com.example.finale.service.jwt;

import com.example.finale.entities.Commande;
import com.example.finale.entities.Reservation;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ReservationService {
    public Long saveReservation(Reservation reservation);

    public List<Reservation> getAllReservations();
    public Reservation getReservationById(Integer id);

    public void deleteReservation(Integer id);
    Integer reservationsEnAttente();

    Reservation refuserReservation(Integer idR);

    Reservation acceptReservation(Integer idR) throws MessagingException;
    void sendConfirmationEmail(String toEmail) throws MessagingException, MessagingException;

    Integer totalReservations();

    Reservation addReservationAndAffect(Reservation reservation, Integer id,Long userId);

    }
