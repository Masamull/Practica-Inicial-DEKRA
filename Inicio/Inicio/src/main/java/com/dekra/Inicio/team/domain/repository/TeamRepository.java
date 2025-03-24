package com.dekra.Inicio.team.domain.repository;

import com.dekra.Inicio.team.domain.model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository {

    Team createTeam(Team t);
    List<Team> listTeam();
    Team updateTeam(Team t);

}
