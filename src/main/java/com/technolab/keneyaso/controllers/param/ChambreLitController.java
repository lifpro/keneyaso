package com.technolab.keneyaso.controllers.param;

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
}
