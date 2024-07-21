package com.example.finale.service.jwt;

import com.example.finale.entities.*;
import com.example.finale.repository.ColocataireRepository;
import com.example.finale.repository.CommandeRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ColocataireServiceImpl implements ColocataireService{

    @Autowired
    private ColocataireRepository repository;


    @Override
    public Long saveColocataire(Colocataire colocataire) {
        return repository.save(colocataire).getIdColocataire();
    }

    @Override
    public List<Colocataire> getAllColocataire() {
        return (List<Colocataire>) repository.findAll();
    }

    @Override
    public Colocataire getColocataireById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteColocataire(Integer sno) {
        repository.deleteById(sno);
    }
    @Override
    public Colocataire refuserColocataire(Integer idR){
        Colocataire r = repository.findById(idR).get();
        r.setRes("Refused");
        repository.save(r);
        return r;
    }
    @Override
    public Colocataire acceptColocataire(Integer idR) throws MessagingException {
        Colocataire r = repository.findById(idR).get();
        r.setRes("Accepted");
        repository.save(r);
        return r;
    }

}
