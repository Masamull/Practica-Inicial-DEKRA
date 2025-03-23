package com.dekra.Inicio.team.infraestructure.repository;

import com.dekra.Inicio.team.domain.model.Team;
import com.dekra.Inicio.team.domain.repository.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TeamRepositoryImp implements TeamRepository {

    private final Set<Team> teamRepository = new HashSet<>();
    private Long currentId = 1L;


    @Override
    public Team createTeam(Team t) {
        t.setTeamId(currentId++);
        teamRepository.add(t);
        return t;
    }

    @Override
    public List<Team> listTeam() {
        return new ArrayList<>(teamRepository);
    }



    @Override

        public Team updateTeam(Team updatedTeam) {
            Optional<Team> existingTeam = teamRepository.stream()
                    .filter(t -> t.getTeamId().equals(updatedTeam.getTeamId()))
                    .findFirst();

            if (existingTeam.isPresent()) {
                teamRepository.remove(existingTeam.get());
                teamRepository.add(updatedTeam);
                return updatedTeam;
            } else {
                throw new IllegalArgumentException("Team does not exist");
            }
        }
    }

