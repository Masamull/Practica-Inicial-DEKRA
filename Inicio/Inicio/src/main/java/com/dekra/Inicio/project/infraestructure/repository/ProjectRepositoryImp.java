package com.dekra.Inicio.project.infraestructure.repository;

import com.dekra.Inicio.project.domain.model.Project;
import com.dekra.Inicio.project.domain.repository.ProjectRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProjectRepositoryImp implements ProjectRepository {

    private final Set<Project> projectRepository = new HashSet<>();
    private Long currentId = 1L;

    @Override
    public List<Project> listProjects() {
        return new ArrayList<>(projectRepository.stream().toList());
    }

    @Override
    public Project createProject(Project project) {
        project.setProjectId(currentId++);
        projectRepository.add(project);

        return project;
    }

    @Override
    public Project updateProject(Project p) {

        if(!projectRepository.contains(p)) {

            throw new IllegalArgumentException("Project does not exist");
        }

        projectRepository.remove(p);
        projectRepository.add(p);

        return p;
    }
}
