package com.shareparty.shareparty_backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareparty.shareparty_backend.models.Party;
import com.shareparty.shareparty_backend.models.User;

public interface PartyRepository  extends JpaRepository<Party, Integer>{
    List<Party> findByUser (User user);
    Optional<Party> findById(int id);
}
