package com.dekra.Inicio.project.api.dto;


import com.dekra.Inicio.project.domain.model.ProjectVersion;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long ProjectId;

    private String ProjectName;

    private String email;

    private List<Rol> rols = new ArrayList<>();

    private List <ProjectVersion> projectVersions = new LinkedList<>();


    public List<ProjectVersion> setProjectVersionAuto(List<ProjectVersion> projectVersions) {
        if (projectVersions == null) {
            projectVersions = new LinkedList<>();
        }
        if (projectVersions.isEmpty()) {
            // Primera versión: projectId + 0.1
            ProjectVersion firstVersion = new ProjectVersion();
            firstVersion.setProjectVersionNumber(this.ProjectId + 0.1f);  // Usamos this.ProjectId para obtener el id actual
            firstVersion.setFechaCambio(LocalDateTime.now());
            // Asignamos los datos del proyecto
            firstVersion.setProjectId(this.ProjectId);
            firstVersion.setProjectName(this.ProjectName);
            firstVersion.setEmail(EmailValue.of(this.email));
            // Si ProjectVersion tiene otros campos, se pueden asignar de forma similar
            projectVersions.add(firstVersion);
        } else {
            // Si ya existen versiones, obtenemos la última versión y sumamos 0.1
            ProjectVersion lastVersion = projectVersions.get(projectVersions.size() - 1);
            float lastVersionNumber = lastVersion.getProjectVersionNumber();

            int major = (int) lastVersionNumber;  // Parte entera
            int minor = (int) ((lastVersionNumber - major) * 10);  // Parte decimal

            if (minor < 9) {
                minor++;
            } else {
                major++;
                minor = 0;
            }
            float nextVersion = major + minor / 10.0f;

            ProjectVersion newVersion = new ProjectVersion();
            newVersion.setProjectVersionNumber(nextVersion);
            newVersion.setFechaCambio(LocalDateTime.now());
            // También aquí asignamos los datos del proyecto
            newVersion.setProjectId(this.ProjectId);
            newVersion.setProjectName(this.ProjectName);
            newVersion.setEmail(EmailValue.of(this.email));

            projectVersions.add(newVersion);
        }

        return projectVersions;
    }

}
