package com.shareparty.shareparty_backend.services;

import org.springframework.stereotype.Service;

import com.shareparty.shareparty_backend.models.Party;
import com.shareparty.shareparty_backend.models.User;
import com.shareparty.shareparty_backend.repositories.PartyRepository;
import com.shareparty.shareparty_backend.repositories.UserRepository;

import java.util.Optional;
import java.util.List;

@Service
public class PartyService {
    private PartyRepository partyRepository;
    private UserRepository userRepository;

    public PartyService(PartyRepository partyRepository){
        this.partyRepository=partyRepository;
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

    public Party saveParty(int userId, Party party){
        User user= userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
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
