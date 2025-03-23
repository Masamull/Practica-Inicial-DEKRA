package com.dekra.Inicio.rol.infraestructure.repository;

import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.rol.domain.repository.RolRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RolRepositoryImp implements RolRepository {

    private final Set<Rol> rolRepository = new HashSet<>();
    private Long currentId = 1L;

    @Override
    public List<Rol> listRol() {
        return new ArrayList<>(rolRepository.stream().toList());
    }

    @Override
    public Rol createRol(Rol rol) {
        rol.setIdRol(currentId++);
        rolRepository.add(rol);

        return rol;
    }
}
