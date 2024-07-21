package com.example.finale.service.jwt;

import com.example.finale.entities.Notification;
import com.example.finale.entities.ReservationTrajet;
import com.example.finale.entities.Restaurant;

import java.util.List;

public interface ReservationTrajetService {

    public Long saveReservationTrajet(ReservationTrajet reservationTrajet);

    public List<ReservationTrajet> getAllReservationTrajets();
    public ReservationTrajet getReservationTrajetById(Integer id);

    public void deleteReservationTrajet(Integer id);
    List<ReservationTrajet> GetReservationsByTrajetId(Integer idRes);
    public int getsumreservations();
    public List<Notification> getAllNotif();
    public List<Notification> getNotifByUser(long UserId);


}
