package com.shareparty.shareparty_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shareparty.shareparty_backend.jwt.AuthResponse;
import com.shareparty.shareparty_backend.jwt.JwtService;
import com.shareparty.shareparty_backend.jwt.LoginRequest;
import com.shareparty.shareparty_backend.jwt.RegisterRequest;
import com.shareparty.shareparty_backend.models.User;
import com.shareparty.shareparty_backend.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PostMapping(value = "register")
    public AuthResponse registerUser(@RequestBody RegisterRequest request) {
        User newCreatedUser = userService.register(request);
        return AuthResponse.builder()
        .token(jwtService.getToken(newCreatedUser))
        .build();
    }
}
