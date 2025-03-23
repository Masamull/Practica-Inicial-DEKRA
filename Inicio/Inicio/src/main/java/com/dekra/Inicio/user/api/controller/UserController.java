package com.dekra.Inicio.user.api.controller;

import com.dekra.Inicio.rol.api.dto.RolDTO;
import com.dekra.Inicio.user.api.dto.UserDTO;
import com.dekra.Inicio.user.application.UserService;
import com.dekra.Inicio.user.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> listUsers() {

        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/domains")
    public ResponseEntity<List<String>> listDomainsGeneral() {

        return ResponseEntity.ok(userService.emailValueListDomains());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {

        UserDTO newUserDTO = userService.createUserDTO(userDTO);

        return ResponseEntity.status(201).body(newUserDTO);
    }




}

