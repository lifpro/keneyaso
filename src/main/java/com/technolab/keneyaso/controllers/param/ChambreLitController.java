package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.entities.hospi.Chambres;
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
    public ResponseEntity save(@RequestBody Chambres c){
        System.out.println(c.toString());
        return ResponseEntity.ok(c) ;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Chambres c){
        System.out.println(c.toString());
        return ResponseEntity.ok(c) ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok("Suppression des Chambres qui a id="+id) ;
    }
}
