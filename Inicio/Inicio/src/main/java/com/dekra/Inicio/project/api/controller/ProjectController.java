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

    private ProjectService projectService;

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
        try {
            // Asegurarse de que el ID del DTO coincide con el del PathVariable
            if (projectDTO.getProjectId() != projectId) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);  // El ID del proyecto no coincide con el proporcionado
            }

            // Llamar al servicio para actualizar el proyecto
            ProjectDTO updatedProjectDTO = projectService.updateProjectDTO(projectDTO);

            // Retornar el DTO actualizado
            return ResponseEntity.ok(updatedProjectDTO);  // Respuesta exitosa con el DTO actualizado

        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}



