package com.shareparty.shareparty_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.shareparty.shareparty_backend.models.Party;
import com.shareparty.shareparty_backend.models.User;
import com.shareparty.shareparty_backend.repositories.PartyRepository;
import com.shareparty.shareparty_backend.repositories.UserRepository;

import java.util.Optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class PartyService {

    @Value("${images.directory}")
    private String imagesDirectory;
    private final PartyRepository partyRepository;
    private final ImageService imageService;
    
    public PartyService(PartyRepository partyRepository,ImageService imageService) {
        this.partyRepository = partyRepository;
        this.imageService = imageService;
    }

    public List<Party> getParties(){
        return partyRepository.findAll();
    }
    
    public Optional<Party> findPartyById(int id){
        if (!partyRepository.existsById(id)) {
            throw new RuntimeException("Party not found");
        }
      return partyRepository.findById(id);
    }

    public Party saveParty(String title, String location, String description, MultipartFile image, User user) throws IOException {
        
        // Aquí manejas la lógica para guardar la imagen en el servidor
        @SuppressWarnings("null")
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        Path imagePath = Paths.get(imagesDirectory + fileName);
                System.out.println(fileName);
        try {
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Cambia la URL de la imagen para incluir el dominio del backend
        String imageUrlString = "http://localhost:3001/images/" + fileName;


        // Crea un nuevo destino con los datos recibidos
        Party party = new Party();
        party.setTitle(title);
        party.setLocation(location);
        party.setDescription(description);
        party.setImageUrl(imageUrlString); 
        party.setUser(user);

        return partyRepository.save(party);
    }

    public void deletePartyById(int id){
        if (!partyRepository.existsById(id)){
            throw new RuntimeException("Party not found");
        }        
        partyRepository.deleteById(id);
    }

    public Party updateParty(Integer id, Party updateParty){
        Party party= partyRepository.findById(id)
            .orElseThrow(()->new RuntimeException ("Party not found"));
        
        party.setTitle(updateParty.getTitle());
        party.setLocation(updateParty.getLocation());
        party.setDescription(updateParty.getDescription());
        party.setImageUrl(updateParty.getImageUrl());
        party.setPartyDate(updateParty.getPartyDate());
        party.setStartTime(updateParty.getStartTime());
        party.setEndTime(updateParty.getEndTime());

        return partyRepository.save(party);
    }
}
