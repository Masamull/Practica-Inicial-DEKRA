package com.dekra.Inicio.user.application;

import com.dekra.Inicio.shared.valueObject.EmailValue.application.EmailValueService;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.shared.valueObject.EmailValue.infraestructure.repository.EmailValueRepositoryImp;
import com.dekra.Inicio.user.api.dto.UserDTO;
import com.dekra.Inicio.user.domain.model.User;
import com.dekra.Inicio.user.domain.repository.UserRepository;
import com.dekra.Inicio.user.infraestructure.repository.UserRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailValueService emailValueService;

    public UserService(UserRepository userRepository, EmailValueService email ) {

        this.userRepository = userRepository;
        this.emailValueService = email;
    }

    public List<UserDTO> listUsers() {

        List<UserDTO> newUsersDTO = new ArrayList<>();

        for (User user : userRepository.listUsers()) {

            newUsersDTO.add(convertUserToUserDTO(user));


        }

        return newUsersDTO;

    }

    public UserDTO createUserDTO(UserDTO userDTO) {

        User newUser = convertUserDTOtoUser(userDTO);

        User saveUser = userRepository.createUser(newUser);

        emailValueService.saveEmailValue(saveUser.getEmail());

        return convertUserToUserDTO(saveUser);


    }

    public List<String> emailValueListDomains(){

        Set<String> dominios = new HashSet<>();

        for(EmailValue emails : emailValueService.emailValueList() ){

            dominios.add(emails.getValue().substring(emails.getValue().indexOf("@")));

        }

        return new ArrayList<>(dominios);

    }


//Métodos para pasar de DTO a Usuario cuando lo que queremos es almacenar
    //En el repositorio.
    private User convertUserDTOtoUser (UserDTO userDTO) {

        return new User(EmailValue.of(userDTO.getEmail()),userDTO.getId(),
                userDTO.getName(),userDTO.getSurname(),userDTO.getRols());


    }
//Método para pasar de Usuarioo a DTO para cuando queremos es
    //devolver datos de la API
    private UserDTO convertUserToUserDTO (User user) {

        return new UserDTO(user.getId(),user.getName(),user.getEmail().getValue()
                ,user.getSurname(),user.getRols());

    }
}
