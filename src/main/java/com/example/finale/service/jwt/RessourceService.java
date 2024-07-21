package com.example.finale.service.jwt;

import com.example.finale.entities.Ressource;

import java.util.List;

public interface RessourceService {

    public Long saveRessource(Ressource ressource);

    public List<Ressource> getAllRessources();
    public Ressource getRessourceById(Integer id);

    public void deleteRessource(Integer id);


}
