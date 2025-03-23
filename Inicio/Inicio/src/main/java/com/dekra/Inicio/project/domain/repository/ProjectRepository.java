package com.dekra.Inicio.project.domain.repository;

import com.dekra.Inicio.project.domain.model.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository {

    List<Project> listProjects();
    Project createProject(Project p);
    Project updateProject(Project p);
}
