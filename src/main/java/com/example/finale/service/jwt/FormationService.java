package com.example.finale.service.jwt;


import com.example.finale.entities.Formation;

import java.util.List;

public interface FormationService {
    public Integer saveFormation(Formation formation);

    public List<Formation> getAllFormations();
    public Formation getFormationById(Integer sno);

    public void deleteFormation(Integer sno);
}
