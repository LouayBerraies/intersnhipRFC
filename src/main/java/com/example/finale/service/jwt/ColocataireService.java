package com.example.finale.service.jwt;

import com.example.finale.entities.Colocataire;
import com.example.finale.entities.Reservation;
import jakarta.mail.MessagingException;

import java.util.List;

public interface ColocataireService {
    public Long saveColocataire(Colocataire colocataire);

    public List<Colocataire> getAllColocataire();
    public Colocataire getColocataireById(Integer id);

    public void deleteColocataire(Integer id);
    Colocataire refuserColocataire(Integer idR);

    Colocataire acceptColocataire(Integer idR) throws MessagingException;

}
