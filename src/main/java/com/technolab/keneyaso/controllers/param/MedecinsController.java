package com.technolab.keneyaso.controllers.param;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medecin")
public class MedecinsController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getMedecin(){
        return "Babla";
    }
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String findMedecins(){
        return "retourn la liste des medecin";
    }
}
