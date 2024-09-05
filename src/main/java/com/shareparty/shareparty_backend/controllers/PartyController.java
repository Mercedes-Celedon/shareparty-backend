package com.shareparty.shareparty_backend.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.shareparty.shareparty_backend.models.Party;
import com.shareparty.shareparty_backend.services.PartyService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
public class PartyController {
    
    private PartyService partyService;

    public PartyController(PartyService partyService){
        this.partyService=partyService;
    }

    @GetMapping("/parties")
    public List<Party> getParties() {
        return partyService.getParties();
    }
    

}
