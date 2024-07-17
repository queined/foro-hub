package com.inarixdono.forohub.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inarixdono.forohub.domain.user.User;
import com.inarixdono.forohub.domain.user.IncomingUserDTO;
import com.inarixdono.forohub.infra.security.JWTDto;
import com.inarixdono.forohub.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    //TODO: Isolate authentication logic
    
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTDto> login(@RequestBody @Valid IncomingUserDTO user) {
        Authentication token = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        Authentication authenticated = authManager.authenticate(token);
        User userAuthenticated = (User) authenticated.getPrincipal();
        return ResponseEntity.ok(tokenService.generateJWT(userAuthenticated));
    }
}
