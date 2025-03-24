package com.dekra.Inicio.project.domain.model;

import com.dekra.Inicio.team.domain.model.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectVersion extends Project {



    private float projectVersionNumber;       // Versión del proyecto
    private LocalDateTime fechaCambio;  // Fecha del cambio


}
