package com.shareparty.shareparty_backend.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shareparty.shareparty_backend.models.Party;
import com.shareparty.shareparty_backend.models.User;
import com.shareparty.shareparty_backend.repositories.PartyRepository;
import com.shareparty.shareparty_backend.services.PartyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class PartyController {
    
    @Autowired
    private final PartyService partyService;

    @Autowired
    private PartyRepository partyRepository;

    @Value("${images.directory}")
    private String imagesDirectory;

    public PartyController(PartyService partyService, PartyRepository partyRepository){
        this.partyService=partyService;
        this.partyRepository=partyRepository;
    }

    @GetMapping("/parties")
    public List<Party> getParties() {
        return partyService.getParties();
    }

    @PostMapping("/parties")
    public ResponseEntity<Party> addParty(
            @RequestParam("title") String title,
            @RequestParam("location") String location,
            @RequestParam("description") String description,
            @RequestParam("imageUrl") MultipartFile image,
            @RequestParam("partyDate") String partyDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        try {
            // Usamos el servicio para manejar la lógica de negocio
            Party savedParty = partyService.saveParty(title, location, description, image,partyDate,startTime, endTime, user);

            // Retornar la fiesta guardada si todo va bien
            return ResponseEntity.ok(savedParty);

        } catch (Exception e) {
            // Manejar el error y retornar una respuesta 500 si algo falla
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }        
    }
    @DeleteMapping("/parties")
    public ResponseEntity<Void>deletePartyById(@RequestParam("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Party party = partyRepository.findById(id)
            .orElseThrow(()->new RuntimeException("destino no encontrado"));
        // Verificar si el usuario autenticado es el creador del destino
        if (party.getUser().getId() != user.getId()) {
            // Retornar 403 Forbidden si el usuario autenticado no es el creador
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // Eliminar el destino si la verificación es exitosa
        partyService.deletePartyById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    @PutMapping("path/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    

}
