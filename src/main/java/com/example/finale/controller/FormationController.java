package com.example.finale.controller;

import com.example.finale.entities.Formation;
import com.example.finale.service.jwt.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formationDetails")
public class FormationController {

    @Autowired
    private FormationService service;
    @PostMapping("/saveFormation")
    public ResponseEntity<String> saveFormation( @RequestBody Formation formation){
        Integer id = service.saveFormation(formation);
        return  new ResponseEntity<String>("Formation with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/formationList")
    public ResponseEntity<List<Formation>> getAllFormationDetails(){
        List<Formation> list = service.getAllFormations();
        return new ResponseEntity<List<Formation>>(list,HttpStatus.OK);
    }

    @GetMapping("/getFormationById/{sno}")
    public ResponseEntity<Formation> getFormationById( @PathVariable("sno")  Integer sno){
        Formation std = service.getFormationById(sno);
        return new ResponseEntity<Formation>(std,HttpStatus.OK);
    }
    @PutMapping("/updateFormation/{sno}")
    public ResponseEntity<String> updateFormation( @PathVariable("sno") Integer sno,  @RequestBody Formation formation){
        Formation stdFromDb = service.getFormationById(sno);
        stdFromDb.setTitle(formation.getTitle());
        stdFromDb.setDescription(formation.getDescription());
        stdFromDb.setLien(formation.getLien());
        Integer id = service.saveFormation(stdFromDb);
        return new ResponseEntity<String>("Formation with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteFormation/{sno}")
    public ResponseEntity<String> deleteFormation(@PathVariable("sno") Integer sno){
        service.deleteFormation(sno);
        return new ResponseEntity<String>("Formation with '"+sno+"' has been deleted",HttpStatus.OK);
    }
}
