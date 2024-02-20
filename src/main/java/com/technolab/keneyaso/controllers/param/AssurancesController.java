package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Assurance;
import com.technolab.keneyaso.entities.param.Examens;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.services.param.AssurancesService;
import com.technolab.keneyaso.services.param.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assurances")
public class AssurancesController {
    @Autowired
    private AssurancesService assurancesService;


    @GetMapping("/find")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(this.assurancesService.findAll()) ;
    }
    @PostMapping("/search")
    public ResponseEntity search(@RequestBody FilterWrapper f){
        return ResponseEntity.ok(this.assurancesService.search(f)) ;
    }
    @GetMapping("{id}")
    public ResponseEntity findOne(@PathVariable Long id){
        return ResponseEntity.ok(assurancesService.findOne(id));
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody Assurance m){
        ResponseWrapper<Assurance> w= this.assurancesService.create(m);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Assurance m){
        ResponseWrapper<Assurance> w= this.assurancesService.update(m);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        ResponseWrapper<Medecins> w= this.assurancesService.delete(id);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }
}
