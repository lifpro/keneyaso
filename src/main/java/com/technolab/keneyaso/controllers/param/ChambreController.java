package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.hospi.Chambres;
import com.technolab.keneyaso.entities.hospi.Lits;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.services.param.ChambreService;
import com.technolab.keneyaso.services.param.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chambre")
public class ChambreController {
    @Autowired
    private ChambreService chambreService;

    @PostMapping()
    public ResponseEntity saveChambre(@RequestBody Chambres o){
        ResponseWrapper<Chambres> w= this.chambreService.createChambre(o);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }

    @PostMapping("/lit")
    public ResponseEntity saveLit(@RequestBody Lits o){
        ResponseWrapper<Lits> w= this.chambreService.createLit(o);
        if(w.isStatus()){
            return ResponseEntity.ok(w.getEntite()) ;
        }else{
            return ResponseEntity.badRequest().body(w.getMessage()) ;
        }
    }
    @GetMapping("/findChambres")
    public ResponseEntity findAllChambres(){
        return ResponseEntity.ok(this.chambreService.findAllChambres()) ;
    }

    @GetMapping("/findLits")
    public ResponseEntity findAllLits(){
        return ResponseEntity.ok(this.chambreService.findAllLits()) ;
    }



}
