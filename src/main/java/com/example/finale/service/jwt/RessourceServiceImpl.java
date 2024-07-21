package com.example.finale.service.jwt;

import com.example.finale.entities.Ressource;
import com.example.finale.entities.RessourceStatus;
import com.example.finale.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RessourceServiceImpl implements RessourceService{

    @Autowired
    private RessourceRepository repository;


    @Override
    public Long saveRessource(Ressource ressource) {
        return repository.save(ressource).getIdRessource();
    }

    @Override
    public List<Ressource> getAllRessources() {
        return (List<Ressource>) repository.findAll();
    }

    @Override
    public Ressource getRessourceById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteRessource(Integer sno) {
        repository.deleteById(sno);
    }

    public List<Ressource> getResourcesByStatus( RessourceStatus status) {
        return repository.findByStatus(status);
    }

}
