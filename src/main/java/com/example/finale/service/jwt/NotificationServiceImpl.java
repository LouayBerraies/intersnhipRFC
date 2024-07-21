package com.example.finale.service.jwt;

import com.example.finale.entities.Notification;
import com.example.finale.entities.Trajet;
import com.example.finale.entities.User;
import com.example.finale.repository.NotificationRepository;
import com.example.finale.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    public NotificationRepository notificationRepository;



    @Override
    public void envoyerNotificationReservation(Trajet trajet, User user) {
        Notification notification = new Notification();
        notification.setType("réservation");
        notification.setMessage("Nouvelle réservation pour le trajet " + trajet.getLieuDepart() + " à " + trajet.getLieuArrivee());
        notification.setUserId(user.getId());
        notification.setSentAt(LocalDateTime.now());
        notification.setTrajet(trajet);
        notificationRepository.save(notification);
    }

    @Override
    public void envoyerNotificationAnnulation(Trajet trajet, User user) {
        Notification notification = new Notification();
        notification.setType("annulation");
        notification.setMessage("Annulation de réservation pour le trajet " + trajet.getLieuDepart() + " à " + trajet.getLieuArrivee());
        notification.setUserId(user.getId());
        notification.setSentAt(LocalDateTime.now());
        notification.setTrajet(trajet);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findAllByUserIdOrderByIdNotification(userId);
    }

    @Override
    public List<Notification> TriNotification(Trajet trajet) {

        List<Notification> notifications = notificationRepository.findByTrajet(trajet);
        return notifications;
    }

    @Override
    public List<Notification> AfficherHistorique() {
        return notificationRepository.findAll();
    }

    @Override
    public void marquerCommeLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setIsRead(true);
            notificationRepository.save(notification);
        }
    }
}
