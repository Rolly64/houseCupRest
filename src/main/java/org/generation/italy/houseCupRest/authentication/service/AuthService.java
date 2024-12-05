package org.generation.italy.houseCupRest.authentication.service;


import org.generation.italy.houseCupRest.authentication.controller.AuthenticationRequest;
import org.generation.italy.houseCupRest.authentication.controller.AuthenticationResponse;
import org.generation.italy.houseCupRest.authentication.controller.RegisterRequest;
import org.generation.italy.houseCupRest.authentication.entity.User;
import org.generation.italy.houseCupRest.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service

public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, UserRepository repository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request) {
        logger.info("Register method called with request: {}", request);

        User user = new User(null, request.getFirstname(), request.getLastname(), request.getEmail(), passwordEncoder.encode(request.getPassword()),request.getRole());
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);

    }
}
