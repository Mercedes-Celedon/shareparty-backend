package com.shareparty.shareparty_backend.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shareparty.shareparty_backend.jwt.AuthResponse;
import com.shareparty.shareparty_backend.jwt.JwtService;
import com.shareparty.shareparty_backend.models.User;
import com.shareparty.shareparty_backend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User register(User userRequest) { 
        String encodedPassword= passwordEncoder.encode(userRequest.getPassword());
        User user = User.builder()
            .name(userRequest.getName())
            .email(userRequest.getEmail())
            .password(encodedPassword)
            .build();
            
        return userRepository.save(user);
    } 
    public AuthResponse login(User userRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        User user=userRepository.findByEmail(userRequest.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }
    
}
