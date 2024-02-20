package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Examens;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.services.param.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/examen")
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    @GetMapping("/find")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(this.examenService.findAll()) ;
    }

    @PostMapping("/search")
    public ResponseEntity search(@RequestBody FilterWrapper f){
        return ResponseEntity.ok(this.examenService.search(f)) ;
    }
    @GetMapping("{code}")
    public ResponseEntity findOne(@PathVariable Long code){
        return ResponseEntity.ok(examenService.findOne(code)) ;
    }
    @PostMapping()
    public ResponseEntity save(@RequestBody Examens e){
        ResponseWrapper<Examens> w= this.examenService.create(e);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody Examens e){
        ResponseWrapper<Examens> w= this.examenService.update(e);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @DeleteMapping("{code}")
    public ResponseEntity delete(@PathVariable Long code){
        ResponseWrapper<Examens> w= this.examenService.delete(code);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }
}
