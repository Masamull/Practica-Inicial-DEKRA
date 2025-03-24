package com.dekra.Inicio.team.application;

import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.logs.domain.repository.LogRepository;
import com.dekra.Inicio.logs.infraestructure.repository.LogRepositoryImp;
import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.project.domain.repository.ProjectRepository;
import com.dekra.Inicio.rol.api.dto.RolDTO;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.rol.domain.repository.RolRepository;
import com.dekra.Inicio.team.api.dto.TeamDTO;
import com.dekra.Inicio.team.domain.model.Team;
import com.dekra.Inicio.team.domain.repository.TeamRepository;
import com.dekra.Inicio.team.infraestructure.repository.TeamRepositoryImp;
import com.dekra.Inicio.user.domain.model.User;
import com.dekra.Inicio.user.domain.repository.UserRepository;
import com.dekra.Inicio.user.infraestructure.repository.UserRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final ProjectRepository projectRepository;
    private final LogService logService;
    private final List<TeamObserver> observers = new ArrayList<>();

    public TeamService(TeamRepository teamRepository,
                       UserRepository userRepository, LogService logService, RolRepository rolRepository,
                       ProjectRepository projectRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.logService = logService;
        this.rolRepository = rolRepository;
    }


    public void addObserver(TeamObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TeamObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(Long teamId, Long userId, String rolName) {
        observers.forEach(observer -> observer.update(teamId, userId, rolName));
    }

    public TeamDTO createTeamDTO(TeamDTO teamDTO) {

        Team team = converTeamDTOtoTeam(teamDTO);

        Team saveTeam = teamRepository.createTeam(team);

        return convertTeamtoTeamDTO(saveTeam);

    }

    public TeamDTO AssingUsertoTeamDTO(Long idUsuario, Long idEquipo, String rolName) {

        boolean exists = false;
        boolean teamExists = false;

        Team team = new Team();
        User userExists = new User();

        for (User user : userRepository.listUsers()) {

            if (idUsuario == user.getId()) {

                exists = true;

                userExists = user;

            }

        }

        if (!exists) {

            throw new IllegalArgumentException("Users doesn´t exist when you assign it to a team ");

        }


        for (Team t : teamRepository.listTeam()) {

            if (t.getTeamId() == idEquipo) {

                teamExists = true;

                team = t;

            }

        }

        if (!teamExists) {

            throw new IllegalArgumentException("Team doesn´t " +
                    "exist when you assign it to a User ");


        }

        Rol newRol = new Rol();
        newRol.setRolName(rolName);
        rolRepository.createRol(newRol);

        // Evitar duplicados
        if (userExists.getRols().stream().noneMatch(r -> r.getRolName().equals(rolName))) {
            userExists.getRols().add(newRol);
            userRepository.updateUser(userExists); // Necesitas implementar este método
        }


        Set<User> TeamUsers = team.getTeamUsers();

        TeamUsers.add(userExists);

        team.setTeamUsers(TeamUsers);

        //
        //    logService.createLog("ASSIGNATION","User: " + userExists.getName() +
        //           "is now assing to the" + team + "team");

        notifyObservers(idEquipo, idUsuario, rolName);  //Lo hacemos como un observador

        teamRepository.updateTeam(team);

        return convertTeamtoTeamDTO(team);


    }
    //Listar Equipos

    public List<TeamDTO> listTeams() {

        List<TeamDTO> newTeamsDTO = new ArrayList<>();

        for (Team t : teamRepository.listTeam()) {

            newTeamsDTO.add(convertTeamtoTeamDTO(t));

        }

        return newTeamsDTO;

    }

    public TeamDTO assignProject(Long idTeam, Long idProject) {

        Optional<Project> existingProject = projectRepository.listProjects().stream()
                .filter(p -> p.getProjectId().equals(idProject))
                .findFirst();
        ;

        if (existingProject.isPresent()) {


            Optional<Team> existingTeam = teamRepository.listTeam().stream()
                    .filter(t -> t.getTeamId().equals(idTeam))
                    .findFirst();


            if (existingTeam.isPresent()) {

                existingTeam.get().setAssociatedProject(existingProject.get());

                teamRepository.updateTeam(existingTeam.get());

                return convertTeamtoTeamDTO(existingTeam.get());


            } else {

                throw new IllegalArgumentException("Team ID invalid when you assign it to a project");

            }

        } else {

            throw new IllegalArgumentException("Project not found when assign it to a team ");

        }


    }


    public Team converTeamDTOtoTeam(TeamDTO teamDTO) {

        return new Team(teamDTO.getTeamId(), teamDTO.getTeamName(),
                teamDTO.getTeamUsers(), teamDTO.getAssociatedProject());


    }


    public TeamDTO convertTeamtoTeamDTO(Team team) {

        return new TeamDTO(team.getTeamId(), team.getTeamName(), team.getTeamUsers(),
                team.getAssociatedProject());
    }
}
