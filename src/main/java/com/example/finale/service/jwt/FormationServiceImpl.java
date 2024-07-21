package com.example.finale.service.jwt;

import com.example.finale.entities.Formation;
import com.example.finale.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormationServiceImpl implements FormationService{
    @Autowired
    private FormationRepository repository;


    @Override
    public Integer saveFormation(Formation formation) {
        return repository.save(formation).getSno();
    }

    @Override
    public List<Formation> getAllFormations() {
        return (List<Formation>) repository.findAll();
    }

    @Override
    public Formation getFormationById(Integer sno) {
        return repository.findById(sno).get();
    }

    @Override
    public void deleteFormation(Integer sno) {
        repository.deleteById(sno);
    }
}
