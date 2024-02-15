package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.entities.param.Medecins;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medecin")
public class MedecinsController {



    @GetMapping("/find")
    public ResponseEntity findAll(){
        return ResponseEntity.ok("Renvoie la liste des medecin") ;
    }
    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok("Renvoie le medecin qui a id="+id) ;
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody Medecins m){
        System.out.println(m.toString());
        return ResponseEntity.ok(m) ;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Medecins m){
        System.out.println(m.toString());
        return ResponseEntity.ok(m) ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok("Suppression du medecin qui a id="+id) ;
    }
}
