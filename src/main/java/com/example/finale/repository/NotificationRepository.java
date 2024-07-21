package com.example.finale.repository;

import com.example.finale.entities.Logement;
import com.example.finale.entities.Notification;
import com.example.finale.entities.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUserIdOrderByIdNotification(long UserId);
    List <Notification> findByTrajet(Trajet trajet);

}
