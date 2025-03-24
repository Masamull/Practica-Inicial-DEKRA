package com.dekra.Inicio.rol.api.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTO {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    private String RolName;
}
