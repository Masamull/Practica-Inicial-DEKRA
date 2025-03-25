package com.dekra.Inicio.rol.application;

import com.dekra.Inicio.rol.api.dto.RolDTO;
import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.rol.domain.repository.RolRepository;
import com.dekra.Inicio.rol.infraestructure.repository.RolRepositoryImp;
import com.dekra.Inicio.team.application.TeamObserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService  {

    private final RolRepository rolRepository;


    public RolService(RolRepositoryImp rolRepository) {

        this.rolRepository = rolRepository;

    }


    public List<RolDTO> listRols() {

        List<RolDTO> newRolsDTO = new ArrayList<>();

        for (Rol rol : rolRepository.listRol()) {

        newRolsDTO.add(convertRoltoRolDTO(rol));

    }

        return newRolsDTO;

}

    public RolDTO createRolDTO(RolDTO rolDTO) {

        Rol newRol = convertRolDTOtoRol(rolDTO);

        Rol saveRol = rolRepository.createRol(newRol);

        return convertRoltoRolDTO(saveRol);


    }





    private Rol convertRolDTOtoRol (RolDTO rolDTO) {

      return new Rol(rolDTO.getIdRol(), rolDTO.getRolName());


    }

    private RolDTO convertRoltoRolDTO(Rol rol) {

        return new RolDTO(rol.getIdRol(), rol.getRolName());

    }
}


