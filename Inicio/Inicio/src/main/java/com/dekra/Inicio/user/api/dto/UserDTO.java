package com.dekra.Inicio.user.api.dto;

import com.dekra.Inicio.rol.domain.model.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {




        private Long id;

        private String name;

        private String email;

        private String surname;

        private List<Rol> rols = new ArrayList<>();

        public UserDTO(String name, String email, String surname, List<Rol> rols) {

                this.name = name;
                this.email = email;
                this.surname = surname;
                this.rols = rols;
        }
}
