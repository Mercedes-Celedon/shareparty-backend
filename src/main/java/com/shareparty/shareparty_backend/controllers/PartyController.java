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
import java.util.Optional;


import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/parties/{id}")
    public ResponseEntity<Party> getPartyById(@PathVariable("id") int id){
        Optional<Party> party = partyService.findPartyById(id);

        if (party.isPresent()) {
            return ResponseEntity.ok(party.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Si no se encuentra, devuelve un 404
        }
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
    @DeleteMapping("/parties/{id}")
    public ResponseEntity<Void>deletePartyById(@PathVariable int id){
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
    @PutMapping("parties/{id}")
    public ResponseEntity<Party> uploadParty(
        @PathVariable int id, 
        @RequestParam("title") String title,
        @RequestParam("location") String location,
        @RequestParam("description") String description,
        @RequestParam("imageUrl") MultipartFile image,
        @RequestParam("partyDate") String partyDate,
        @RequestParam("startTime") String startTime,
        @RequestParam("endTime") String endTime) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Party party = partyService.findPartyById(id)
                .orElseThrow(() -> new RuntimeException("Destino no encontrado"));

        // Verificar si el usuario autenticado es el creador del destino
        if (party.getUser().getId() != user.getId()) {
            // Retornar 403 Forbidden si el usuario autenticado no es el creador
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        // Actualizar los campos del destino existente

        // Si se proporciona una nueva imagen, actualizarla
        /* if (image != null && !image.isEmpty()) {
            String fileName = StringUtils.cleanPath(imageUrl.getOriginalFilename());
            Path imagePath = Paths.get(imagesDirectory + fileName);
            try {
                Files.copy(imageUrl.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
                String imageUrlString = "http://localhost:3001/images/" + fileName;
                destination.setImageUrl(imageUrlString);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } */
        try {
            // Usamos el servicio para manejar la lógica de negocio
            Party saveParty = partyService.updateParty(id, title, location, description, image,partyDate,startTime, endTime, user);

            // Retornar la fiesta guardada si todo va bien
            return ResponseEntity.ok(saveParty);

        } catch (Exception e) {
            // Manejar el error y retornar una respuesta 500 si algo falla
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }  
    }
    

}
