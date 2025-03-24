package com.dekra.Inicio.project.application;

import com.dekra.Inicio.logs.application.LogService;
import com.dekra.Inicio.project.api.dto.ProjectDTO;
import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.project.domain.repository.ProjectRepository;
import com.dekra.Inicio.project.infraestructure.repository.ProjectRepositoryImp;
import com.dekra.Inicio.shared.valueObject.EmailValue.application.EmailValueService;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.shared.valueObject.EmailValue.infraestructure.repository.EmailValueRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private EmailValueService emailValueService;
    private LogService logService;

    public ProjectService(ProjectRepository projectRepository,
            EmailValueService emailValueService){

        this.projectRepository = projectRepository;
        this.emailValueService = emailValueService;
    }

    public List<ProjectDTO> listProjects() {

        List<ProjectDTO> newProjectsDTO= new ArrayList<>();

        for (Project project : projectRepository.listProjects()) {

            newProjectsDTO.add(convertProjecttoProjectDTO(project));

        }

        return newProjectsDTO;

    }

    //Creamos el projecto y lo transformamos de DTO  a Usuario para guardarlo
    //Guardamos los correos nuevos que hemos creado
    public ProjectDTO createProjectDTO(ProjectDTO projectDTO) {

        Project newProject = convertProjectDTOtoProject(projectDTO);

        Project saveProject = projectRepository.createProject(newProject);

        emailValueService.saveEmailValue(saveProject.getEmail());

        return convertProjecttoProjectDTO(saveProject);


    }

    public List<String> emailValueListDomains() {

        Set<String> dominios = new HashSet<>();

        for (EmailValue emails : emailValueService.emailValueList()) {

            dominios.add(emails.getValue().substring(emails.getValue().indexOf("@")));

        }

        return new ArrayList<>(dominios);

    }

    public ProjectDTO updateProjectDTO(ProjectDTO projectDTO) {


        for (Project project : projectRepository.listProjects()) {

            if (project.getProjectId() == projectDTO.getProjectId()) {

                projectDTO.setProjectVersions(project.getProjectVersion());

                logService.createLog("PROJECT-UPDATE","The project that was name" +
                        project.getProjectName() + "is now named " + projectDTO.getProjectName());

            }


        }

        Project p =  convertProjectDTOtoProject(projectDTO);

        projectRepository.updateProject(p);

        ProjectDTO projectDTO1 = convertProjecttoProjectDTO(p);

        return projectDTO1;




    }





    private Project convertProjectDTOtoProject (ProjectDTO projectDTO) {

        return new Project(projectDTO.getProjectId(), projectDTO.getProjectName(),
                EmailValue.of(projectDTO.getEmail()),projectDTO.getRols(),projectDTO.getProjectVersions());



    }

    private ProjectDTO convertProjecttoProjectDTO (Project project) {

        return new ProjectDTO(project.getProjectId(), project.getProjectName(),
                project.getEmail().getValue(), project.getRols(),project.getProjectVersion());

    }
}
