package com.dekra.Inicio.team.api.dto;

import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.user.domain.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO {


    private Long teamId;

    private String teamName;

    private Set<User> teamUsers = new HashSet<>();

    private Project associatedProject  =new Project();
}