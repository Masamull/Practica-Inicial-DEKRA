package com.dekra.Inicio.team.api.controller;

import com.dekra.Inicio.team.api.dto.TeamDTO;
import com.dekra.Inicio.team.application.TeamService;
import com.dekra.Inicio.team.domain.model.Team;
import com.dekra.Inicio.user.api.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {

        TeamDTO teamDTO1 = teamService.createTeamDTO(teamDTO);

        return ResponseEntity.status(201).body(teamDTO1);
    }

    @PostMapping("/{teamId}/users/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long teamId,
                                              @PathVariable Long id,
                                              @RequestParam String rol) {



        TeamDTO updatedTeam = teamService.AssingUsertoTeamDTO(id, teamId,rol);

        return ResponseEntity.ok(updatedTeam);




    }

    @PostMapping("/{teamId}/projects/{projectId}")
    public ResponseEntity<TeamDTO> assignProject (@PathVariable Long teamId,
    @PathVariable Long projectId) {


        TeamDTO assignedTeam = teamService.assignProject(teamId, projectId);

        return ResponseEntity.ok(assignedTeam);

    }


    @GetMapping
    public ResponseEntity<List<TeamDTO>> listTeams() {

        return ResponseEntity.ok(teamService.listTeams());
    }

}
