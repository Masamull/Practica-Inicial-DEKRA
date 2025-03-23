package com.dekra.Inicio.project.api.dto;


import com.dekra.Inicio.project.domain.model.ProjectVersion;
import com.dekra.Inicio.rol.domain.model.Rol;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long ProjectId;

    private String ProjectName;

    private String email;

    private List<Rol> rols = new ArrayList<>();

    private List <ProjectVersion> projectVersions = new LinkedList<>();
}
