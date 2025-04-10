package com.dekra.Inicio.project.api.controller;

import com.dekra.Inicio.project.api.dto.ProjectDTO;
import com.dekra.Inicio.project.application.ProjectService;
import com.dekra.Inicio.project.domain.model.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {

        this.projectService = projectService;

    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> listProjects() {

        return  ResponseEntity.ok(projectService.listProjects());

    }

    @GetMapping("/domains")
    public ResponseEntity<List<String>> listDomainsGeneral () {

        return ResponseEntity.ok(projectService.emailValueListDomains());

    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {

        ProjectDTO projectDTO1 = projectService.createProjectDTO(projectDTO);

        return ResponseEntity.status(201).body(projectDTO1);

    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable("projectId") Long projectId,
                                                    @RequestBody ProjectDTO projectDTO) {


            // Llamar al servicio para actualizar el proyecto
            ProjectDTO updatedProjectDTO = projectService.updateProjectDTO(projectDTO);

            // Retornar el DTO actualizado
            return ResponseEntity.ok(updatedProjectDTO);  // Respuesta exitosa con el DTO actualizado

    }
}



