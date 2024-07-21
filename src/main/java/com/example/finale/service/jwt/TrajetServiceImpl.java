package com.example.finale.service.jwt;

import com.example.finale.entities.Formation;
import com.example.finale.entities.Notification;
import com.example.finale.entities.Trajet;
import com.example.finale.repository.NotificationRepository;
import com.example.finale.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrajetServiceImpl implements TrajetService{

    @Autowired
    private TrajetRepository repository;
    @Autowired
    private NotificationRepository notifrepository;


    @Override
    public Long saveTrajet(Trajet trajet) {
        return repository.save(trajet).getIdTrajet();
    }

    @Override
    public List<Trajet> getAllTrajets() {
        return (List<Trajet>) repository.findAll();
    }

    @Override
    public Trajet getTrajetById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteTrajet(Integer sno) {
        Trajet trajet = repository.findById(sno).orElse(null);

        if (trajet != null) {
            List<Notification> notifications = notifrepository.findByTrajet(trajet);

            for (Notification notification : notifications) {
                notification.setTrajet(null);
                notifrepository.save(notification);
            }

            repository.deleteById(sno);
        }
    }

    @Override
    public int getTotalAvailableSeats() {
        List<Trajet> trajets = repository.findAll();
        int totalSeats = 0;
        for (Trajet trajet : trajets) {
            totalSeats += trajet.getPlacesDispo();
        }
        return totalSeats;
    }

    @Override
    public List<Trajet> RechercheTrajet(String dep, String arriv) {
        List<Trajet> trajets = repository.findByLieuDepartAndAndLieuArrivee(dep, arriv);
        return trajets;
    }

    @Override
    public List<Trajet> RechercheTrajetDep(String dep) {
        List<Trajet> trajets = repository.findByLieuDepart(dep);
        return trajets;    }
}


