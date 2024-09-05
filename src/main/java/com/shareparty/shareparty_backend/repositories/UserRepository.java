package com.shareparty.shareparty_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shareparty.shareparty_backend.models.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository <User, Integer> {
    Optional<User> findByName(String name);
    Optional<User> findByEmail(String email);
}
