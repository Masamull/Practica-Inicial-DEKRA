package com.dekra.Inicio.rol.domain.repository;

import com.dekra.Inicio.rol.domain.model.Rol;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolRepository {

    List<Rol> listRol();
    Rol createRol (Rol rol);

}
