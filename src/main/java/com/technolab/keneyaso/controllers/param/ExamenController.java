package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.entities.param.Examens;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen")
public class ExamenController {
    @GetMapping("/find")
    public ResponseEntity findAll(){
        return ResponseEntity.ok("Renvoie la liste des Examens") ;
    }
    @GetMapping("{code}")
    public ResponseEntity findOne(@PathVariable Long code){
        return ResponseEntity.ok("Renvoie l'Examen  qui a Code="+code) ;
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody Examens e){
        System.out.println(e.toString());
        return ResponseEntity.ok(e) ;
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Examens e){
        System.out.println(e.toString());
        return ResponseEntity.ok(e) ;
    }

    @DeleteMapping("{code}")
    public ResponseEntity delete(@PathVariable Long code){
        return ResponseEntity.ok("Suppression de l'examen qui a code="+code) ;
    }
}
