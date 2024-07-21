package com.example.finale.service.jwt;

import com.example.finale.entities.Notification;
import com.example.finale.entities.ReservationTrajet;
import com.example.finale.entities.Trajet;
import com.example.finale.entities.User;
import com.example.finale.repository.NotificationRepository;
import com.example.finale.repository.ReservationTrajetRepository;
import com.example.finale.repository.TrajetRepository;
import com.example.finale.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReservationTrajetImpl implements ReservationTrajetService{

    @Autowired
    private ReservationTrajetRepository repository;
    @Autowired
    private TrajetRepository trajetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    @Override
    public Long saveReservationTrajet(ReservationTrajet reservationTrajet) {
        User user = reservationTrajet.getUser();
        if (user == null) {
            throw new IllegalArgumentException("Reservation must be associated with a valid user.");
        }

        if (user.getId() == null) {
            user = userRepository.save(user);
            reservationTrajet.setUser(user);
        }

        Trajet trajet = reservationTrajet.getTrajet();
        if (trajet == null) {
            throw new IllegalArgumentException("Reservation must be associated with a valid trajet.");
        }
        Long idTrajetLong = reservationTrajet.getTrajet().getIdTrajet();

        Integer idTrajetInteger = idTrajetLong.intValue();
        trajet = trajetRepository.findById(idTrajetInteger)
                .orElseThrow(() -> new EntityNotFoundException("Trajet not found with ID: " ));

        int nbPassager = reservationTrajet.getNbPassager();
        if (nbPassager <= 0) {
            throw new IllegalArgumentException("Number of passengers must be greater than zero.");
        }

        long availableSeats = trajet.getPlacesDispo() - nbPassager;
        if (availableSeats < 0) {
            throw new IllegalStateException("Insufficient available seats for the reservation.");
        }
        Notification notification = new Notification();
        notification.setType("réservation");
        notification.setMessage("Nouvelle réservation pour le trajet " + trajet.getLieuDepart() + " à " + trajet.getLieuArrivee() + "Nombre de places réservé :  " + reservationTrajet.getNbPassager());
        notification.setUserId(user.getId());
        notification.setIsRead(Boolean.FALSE);
        notification.setSentAt(LocalDateTime.now());
        notification.setTrajet(trajet);
        notificationRepository.save(notification);
        trajet.setPlacesDispo(availableSeats);
        trajetRepository.save(trajet);

        reservationTrajet = repository.save(reservationTrajet);

        return reservationTrajet.getIdReservation();

    }



    @Override
    public List<ReservationTrajet> getAllReservationTrajets() {
        return (List<ReservationTrajet>) repository.findAll();
    }

    @Override
    public ReservationTrajet getReservationTrajetById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteReservationTrajet(Integer id) {
        ReservationTrajet reservationTrajet = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReservationTrajet not found with ID: " + id));
        User user = userRepository.findById(reservationTrajet.getUser().getId()).orElse(null);
        Trajet trajet = reservationTrajet.getTrajet();
        if (trajet != null) {
            int nbPassager = reservationTrajet.getNbPassager();

            long updatedPlacesDispo = trajet.getPlacesDispo() + nbPassager;
            trajet.setPlacesDispo(updatedPlacesDispo);
            trajetRepository.save(trajet);
            

        }
        Notification notification = new Notification();
        notification.setType("annulation");
        notification.setMessage("Annulation de réservation pour le trajet " + trajet.getLieuDepart() + " à " + trajet.getLieuArrivee());
        notification.setUserId(user.getId());
        notification.setSentAt(LocalDateTime.now());
        notification.setTrajet(trajet);
        notificationRepository.save(notification);
        repository.delete(reservationTrajet);

    }


    @Override
    public List<ReservationTrajet> GetReservationsByTrajetId(Integer idTrajet) {
        return repository.findByTrajetIdTrajet(idTrajet);
    }
    @Override
    public int getsumreservations() {
        List<ReservationTrajet> reservationTrajets = repository.findAll();
        return reservationTrajets.size();
    }

    @Override
    public List<Notification> getAllNotif() {return notificationRepository.findAll();}

    @Override
    public List<Notification> getNotifByUser(long UserId) {
        return notificationRepository.findAllByUserIdOrderByIdNotification(UserId);
    }

}
