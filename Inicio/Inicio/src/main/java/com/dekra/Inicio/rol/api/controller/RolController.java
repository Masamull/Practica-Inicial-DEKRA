package com.dekra.Inicio.rol.api.controller;

import com.dekra.Inicio.project.api.dto.ProjectDTO;
import com.dekra.Inicio.rol.api.dto.RolDTO;
import com.dekra.Inicio.rol.application.RolService;
import com.dekra.Inicio.rol.domain.model.Rol;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rols")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {

        this.rolService = rolService;

    }

     @GetMapping
    public ResponseEntity<List<RolDTO>> listAllRols() {

        return ResponseEntity.ok(rolService.listRols());

     }

     @PostMapping
    public ResponseEntity<RolDTO> createRol(@RequestBody RolDTO rolDTO) {

        RolDTO rol = rolService.createRolDTO(rolDTO);

        return ResponseEntity.status(201).body(rol);

     }

}
