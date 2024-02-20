package com.technolab.keneyaso.controllers.param;

import com.technolab.keneyaso.entities.param.Examens;
import com.technolab.keneyaso.services.param.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    CustomService customService;

    @GetMapping("/convert")
    public ResponseEntity convert(){
        return ResponseEntity.ok(customService.convert()) ;
    }

}
