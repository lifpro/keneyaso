package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.repositories.param.MedecinsRepository;
import com.technolab.keneyaso.services.param.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecin")
public class MedecinsController {

    @Autowired
    private MedecinService medecinService;


    @GetMapping("/find")
    public ResponseEntity findAll(){
       return ResponseEntity.ok(this.medecinService.findAll()) ;
    }
    @PostMapping("/search")
    public ResponseEntity search(@RequestBody FilterWrapper f){
        return ResponseEntity.ok(this.medecinService.search(f)) ;
    }
    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(medecinService.findOne(id));
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody Medecins m){
        ResponseWrapper<Medecins> w= this.medecinService.create(m);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Medecins m){
        ResponseWrapper<Medecins> w= this.medecinService.update(m);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ResponseWrapper<Medecins> w= this.medecinService.delete(id);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }
}
