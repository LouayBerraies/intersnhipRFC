package com.example.finale.service.jwt;

import com.example.finale.entities.Notification;
import com.example.finale.entities.Trajet;
import com.example.finale.entities.User;

import java.util.List;

public interface NotificationService {
    void envoyerNotificationReservation(Trajet trajet, User user);
    void envoyerNotificationAnnulation(Trajet trajet, User user);
    void marquerCommeLue(Long notificationId);
    List<Notification> getNotificationsForUser(Long userId);

    List<Notification> TriNotification (Trajet trajet);
    List<Notification> AfficherHistorique ();



}
