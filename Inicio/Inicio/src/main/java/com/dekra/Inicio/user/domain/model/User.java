package com.dekra.Inicio.user.domain.model;


import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private EmailValue email;


    private Long id;

    private String name;

    private String surname;

    private List<Rol> rols = new ArrayList<>();


    public void addRol(Rol rol) {
        this.rols.add(rol);
    }

}
