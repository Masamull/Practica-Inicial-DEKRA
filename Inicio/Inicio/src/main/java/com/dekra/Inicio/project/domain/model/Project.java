package com.dekra.Inicio.project.domain.model;

import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {


    private Long projectId;

    private String projectName;

    private EmailValue email;

    private List<Rol> rols = new ArrayList<>();

    private List<ProjectVersion> projectVersion = new LinkedList<>();


    public List<ProjectVersion> setProjectVersionAuto(List<ProjectVersion> projectVersions) {
        if (projectVersions == null || projectVersions.isEmpty()) {
            // Si la lista de versiones está vacía, asigna la primera versión: projectId + 0.1
            ProjectVersion firstVersion = new ProjectVersion();
            firstVersion.setProjectVersionNumber(projectId + 0.1f);  // Primera versión
            firstVersion.setFechaCambio(LocalDateTime.now());
            projectVersions.add(firstVersion);
        } else {
            // Si ya existen versiones, obtenemos la última versión y sumamos 0.1
            ProjectVersion lastVersion = projectVersions.get(projectVersions.size() - 1);
            float lastVersionNumber = lastVersion.getProjectVersionNumber();

            // Separar la parte entera y la decimal
            int major = (int) lastVersionNumber;  // Parte entera
            int minor = (int) ((lastVersionNumber - major) * 10);  // Parte decimal

            // Si la parte decimal es menor que 9, aumentamos la parte decimal
            if (minor < 9) {
                minor++;
            } else {
                // Si la parte decimal es 9, subimos la parte entera
                major++;
                minor = 0;
            }

            // Nueva versión
            float nextVersion = major + minor / 10.0f;

            ProjectVersion newVersion = new ProjectVersion();
            newVersion.setProjectVersionNumber(nextVersion);
            newVersion.setFechaCambio(LocalDateTime.now());
            projectVersions.add(newVersion);
        }

        return projectVersions;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectId, project.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

}
