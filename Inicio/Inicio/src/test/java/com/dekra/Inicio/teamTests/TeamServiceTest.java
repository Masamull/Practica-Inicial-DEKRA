package com.dekra.Inicio.teamTests;

import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.logs.infraestructure.repository.LogRepositoryImp;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.team.api.dto.TeamDTO;
import com.dekra.Inicio.team.application.TeamService;
import com.dekra.Inicio.team.domain.model.Team;
import com.dekra.Inicio.team.domain.repository.TeamRepository;
import com.dekra.Inicio.team.infraestructure.repository.TeamRepositoryImp;
import com.dekra.Inicio.user.domain.model.User;
import com.dekra.Inicio.user.domain.repository.UserRepository;
import com.dekra.Inicio.user.infraestructure.repository.UserRepositoryImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private UserRepository userRepository;


    @Mock
    private LogService logService;

    @InjectMocks
    private TeamService teamService;

    private TeamDTO sampleTeamDTO;
    private Team sampleTeam;
    private User sampleUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Datos de ejemplo
        sampleUser = new User(EmailValue.of("correo@dekra.org"),1L, "John Doe", "john@example.com",new ArrayList<>());
        Set<User> teamUsers = new HashSet<>();
        teamUsers.add(sampleUser);

        sampleTeam = new Team(
                1L,
                "Dev Team",
                teamUsers,null
        );

        sampleTeamDTO = new TeamDTO(
                sampleTeam.getTeamId(),
                sampleTeam.getTeamName(),
                sampleTeam.getTeamUsers(),
                sampleTeam.getAssociatedProject()
        );
    }

    // Test para crear un TeamDTO exitosamente
    @Test
    void createTeamDTO_Success() {
        // Configurar mocks
        when(teamRepository.createTeam(any(Team.class))).thenReturn(sampleTeam);

        // Ejecutar método
        TeamDTO result = teamService.createTeamDTO(sampleTeamDTO);

        // Verificaciones
        verify(teamRepository, times(1)).createTeam(any(Team.class));
        assertEquals(sampleTeamDTO.getTeamId(), result.getTeamId());
        assertEquals(sampleTeamDTO.getTeamName(), result.getTeamName());
    }

    // Test para asignar un usuario a un equipo exitosamente
    @Test
    void AssignUsertoTeamDTO_Success_WithRole() {
        // Configurar mocks
        List<User> users = new ArrayList<>();
        users.add(sampleUser);
        when(userRepository.listUsers()).thenReturn(users);

        List<Team> teams = new ArrayList<>();
        teams.add(sampleTeam);
        when(teamRepository.listTeam()).thenReturn(teams);

        // Mockear actualización de usuario
        when(userRepository.updateUser(any(User.class))).thenReturn(sampleUser);

        // Ejecutar método con rol
        TeamDTO result = teamService.AssingUsertoTeamDTO(1L, 1L, "TEAM_MEMBER");

        // Verificaciones
        verify(teamRepository, times(1)).updateTeam(any(Team.class));
        verify(userRepository, times(1)).updateUser(any(User.class));

        // Verificar que se añadió el rol
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).updateUser(userCaptor.capture());
        assertTrue(
                userCaptor.getValue().getRols().stream()
                        .anyMatch(r -> "TEAM_MEMBER".equals(r.getRolName()))
        );

        verify(logService, times(1)).createLog(
                eq("ASSIGNATION"),
                contains("is now assing to the")
        );
    }

    // Tests actualizados con parámetro de rol
    @Test
    void AssignUsertoTeamDTO_UserNotFound() {
        when(userRepository.listUsers()).thenReturn(new ArrayList<>());

        assertThrows(
                IllegalArgumentException.class,
                () -> teamService.AssingUsertoTeamDTO(1L, 1L, "TEAM_MEMBER") // Añadir rol
        );
    }

    @Test
    void AssignUsertoTeamDTO_TeamNotFound() {
        List<User> users = new ArrayList<>();
        users.add(sampleUser);
        when(userRepository.listUsers()).thenReturn(users);
        when(teamRepository.listTeam()).thenReturn(new ArrayList<>());

        assertThrows(
                IllegalArgumentException.class,
                () -> teamService.AssingUsertoTeamDTO(1L, 1L, "TEAM_MEMBER") // Añadir rol
        );
    }

    // Test para listar equipos
    @Test
    void listTeams_Success() {
        List<Team> teams = new ArrayList<>();
        teams.add(sampleTeam);
        when(teamRepository.listTeam()).thenReturn(teams);

        List<TeamDTO> result = teamService.listTeams();

        assertEquals(1, result.size());
        assertEquals(sampleTeamDTO.getTeamName(), result.get(0).getTeamName());
    }

    // Test para conversión de Team a TeamDTO
    @Test
    void testConvertTeamtoTeamDTO() {
        TeamDTO converted = teamService.convertTeamtoTeamDTO(sampleTeam);
        assertEquals(sampleTeam.getTeamId(), converted.getTeamId());
        assertEquals(sampleTeam.getTeamName(), converted.getTeamName());
    }

    // Test para conversión de TeamDTO a Team
    @Test
    void testConverTeamDTOtoTeam() {
        Team converted = teamService.converTeamDTOtoTeam(sampleTeamDTO);
        assertEquals(sampleTeamDTO.getTeamId(), converted.getTeamId());
        assertEquals(sampleTeamDTO.getTeamName(), converted.getTeamName());
    }
}



