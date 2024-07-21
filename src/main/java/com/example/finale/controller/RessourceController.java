package com.example.finale.controller;

import com.example.finale.entities.Ressource;
import com.example.finale.service.jwt.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/ressourceDetails")
public class RessourceController {

    @Autowired
    private RessourceService service;

    @PostMapping("/saveressource")
    public ResponseEntity<String> saveFormation(@RequestBody Ressource ressource){
        Long id = service.saveRessource(ressource);
        return  new ResponseEntity<String>("Ressource with '"+id+"' has been saved", HttpStatus.OK);

    }
    @GetMapping("/ressourceList")
    public ResponseEntity<List<Ressource>> getAllRessourceDetails(){
        List<Ressource> list = service.getAllRessources();
        return new ResponseEntity<List<Ressource>>(list,HttpStatus.OK);
    }

    @GetMapping("/getRessourceById/{sno}")
    public ResponseEntity<Ressource> getRessourceById( @PathVariable("sno")  Integer sno){
        Ressource std = service.getRessourceById(sno);
        return new ResponseEntity<Ressource>(std,HttpStatus.OK);
    }
    @PutMapping("/updateRessource/{sno}")
    public ResponseEntity<String> updateRessource( @PathVariable("sno") Integer sno,  @RequestBody Ressource ressource){

        Ressource stdFromDb = service.getRessourceById(sno);

        stdFromDb.setLabel(ressource.getLabel());
        stdFromDb.setStatus(ressource.getStatus());
        stdFromDb.setTypeRessource(ressource.getTypeRessource());
        stdFromDb.setCapacite(ressource.getCapacite());



        Long id = service.saveRessource(stdFromDb);
        return new ResponseEntity<String>("Ressource with '"+id+"' has been updated",HttpStatus.OK);

    }
    @DeleteMapping("deleteRessource/{sno}")
    public ResponseEntity<String> deleteRessource(@PathVariable("sno") Integer sno){
        service.deleteRessource(sno);
        return new ResponseEntity<String>("Ressource with '"+sno+"' has been deleted",HttpStatus.OK);
    }
   /* @GetMapping("/api/availability")
    public List<Ressource> getResourcesByStatus(@RequestParam RessourceStatus status) {
        return iRessource.getResourcesByStatus(status);
    }*/
}
