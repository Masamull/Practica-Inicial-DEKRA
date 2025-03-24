package com.dekra.Inicio.team.domain.model;



import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.user.domain.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {


    private Long teamId;

    private String teamName;

    private Set<User> teamUsers = new HashSet<>();

    private Project associatedProject  =new Project();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }
}