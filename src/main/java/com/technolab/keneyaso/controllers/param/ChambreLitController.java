package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.entities.hospi.ChambreLit;
import com.technolab.keneyaso.entities.param.Medecins;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chambre")
public class ChambreLitController {

    @GetMapping("/find")
    public ResponseEntity findAll(){
        return ResponseEntity.ok("Liste des chambres");
    }
    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable long id){
        return ResponseEntity.ok("La chambre avec l'id: "+ id);
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody ChambreLit c){
        System.out.println(c.toString());
        return ResponseEntity.ok(c) ;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody ChambreLit c){
        System.out.println(c.toString());
        return ResponseEntity.ok(c) ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok("Suppression des Chambres qui a id="+id) ;
    }
}
