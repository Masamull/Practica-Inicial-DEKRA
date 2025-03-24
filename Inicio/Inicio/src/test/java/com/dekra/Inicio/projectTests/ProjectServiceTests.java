package com.dekra.Inicio.projectTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.project.api.dto.ProjectDTO;
import com.dekra.Inicio.project.application.ProjectService;
import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.project.domain.model.ProjectVersion;
import com.dekra.Inicio.project.domain.repository.ProjectRepository;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.application.EmailValueService;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.management.relation.Role;
import java.util.*;

public class ProjectServiceTests {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EmailValueService emailValueService;

    @Mock
    private LogService logService;

    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository, emailValueService);

    }

    // Test de la funcionalidad listProjects()
    @Test
    public void testListProjects() {
        // Datos simulados

        List<Rol> roles = new ArrayList<>();
        List<ProjectVersion> projectVersions = new ArrayList<>();

        Project project = new Project(1L, "Project 1", EmailValue.of("test@example.com"),roles , projectVersions);
        when(projectRepository.listProjects()).thenReturn(Arrays.asList(project));

        // Llamada al método
        List<ProjectDTO> result = projectService.listProjects();

        // Verificación
        assertNotNull(result);
        assertEquals(1L, result.size());
        assertEquals("Project 1", result.get(0).getProjectName());
        verify(projectRepository, times(1)).listProjects();
    }

    // Test de la funcionalidad createProjectDTO()
    @Test
    public void testCreateProjectDTO() {
        // Datos simulados


        ProjectDTO projectDTO = new ProjectDTO(1L, "Project 1", "test@example.com", null, null);
        Project project = new Project(1L, "Project 1", EmailValue.of("test@example.com"), null, null);

        when(projectRepository.createProject(any(Project.class))).thenReturn(project);

        // Simula que se guardan los emails
        when(emailValueService.saveEmailValue(any(EmailValue.class))).thenReturn(EmailValue.of("test@example.com"));

        // Llamada al método
        ProjectDTO result = projectService.createProjectDTO(projectDTO);

        // Verificación
        assertNotNull(result);
        assertEquals("Project 1", result.getProjectName());
        verify(projectRepository, times(1)).createProject(any(Project.class));
        verify(emailValueService, times(1)).saveEmailValue(any(EmailValue.class));
    }



    // Test de la funcionalidad emailValueListDomains()
    @Test
    public void testEmailValueListDomains() {
        // Datos simulados
        EmailValue email1 = new EmailValue("test@example.com");
        EmailValue email2 = new EmailValue("user@domain.com");
        when(emailValueService.emailValueList()).thenReturn(Arrays.asList(email1, email2));

        // Llamada al método
        List<String> domains = projectService.emailValueListDomains();

        // Verificación
        assertNotNull(domains);
        assertEquals(2, domains.size());
        assertTrue(domains.contains("@example.com"));
        assertTrue(domains.contains("@domain.com"));
    }
}
