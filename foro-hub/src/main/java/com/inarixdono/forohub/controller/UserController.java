package com.inarixdono.forohub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.inarixdono.forohub.domain.user.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid NewUserDTO userDTO, UriComponentsBuilder builder) {
        UserDTO user = new UserDTO(userService.createUser(userDTO));
        URI uri = UriBuilder.buildForId(builder, "/users/{id}", user.id().toString());
        return ResponseEntity.created(uri).body(user);
    }
}
