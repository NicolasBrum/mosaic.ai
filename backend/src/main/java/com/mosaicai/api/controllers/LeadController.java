package com.mosaicai.api.controllers;

import com.mosaicai.api.models.LeadModel;
import com.mosaicai.api.services.LeadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/lead")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://127.0.0.1:5500"})
public class LeadController {
    private final LeadService leadService;

    @PostMapping
    public ResponseEntity<HttpStatus> persistLead(@RequestBody LeadModel lead){
        try{
            leadService.save(lead);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
