package com.dekra.Inicio.userTests;

import com.dekra.Inicio.rol.domain.model.Rol;
import com.dekra.Inicio.shared.valueObject.EmailValue.application.EmailValueService;
import com.dekra.Inicio.shared.valueObject.EmailValue.domain.model.EmailValue;
import com.dekra.Inicio.user.api.dto.UserDTO;
import com.dekra.Inicio.user.application.UserService;
import com.dekra.Inicio.user.domain.model.User;
import com.dekra.Inicio.user.infraestructure.repository.UserRepositoryImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepositoryImp userRepository;

    @Mock
    private EmailValueService emailValueService;

    @InjectMocks
    private UserService userService;

    @DisplayName("Lista todos los usuarios")
    @Test
    void testListUsers() {

        //Lista de usuarios para probar
        User user1 = new User(EmailValue.of("user1@example.com"), 1L, "Nombre1",
                "Apellido1", List.of(new Rol()));
        User user2 = new User(EmailValue.of("user2@example.com"), 2L, "Nombre2",
                "Apellido2", List.of(new Rol()));
        List<User> listaUsuarios = asList(user1, user2);

        when(userRepository.listUsers()).thenReturn(listaUsuarios);

        // Ejecución: se invoca el método a testear.
        List<UserDTO> listaDTO = userService.listUsers();

        // Verificación: se comprueba que la lista devuelta tenga la misma cantidad de elementos y que la conversión sea correcta.
        assertEquals(2, listaDTO.size());
        assertEquals("user1@example.com", listaDTO.get(0).getEmail());
        assertEquals("user2@example.com", listaDTO.get(1).getEmail());
    }

    @DisplayName("Crear Usuario y confirmar que coincide el IDdelDTO con el del Usuario")
    @Test
    void testCreateUserDTO() {
        // Preparación: definimos el UserDTO de entrada y el usuario que se supone que devuelve el repositorio.
        UserDTO entradaDTO = new UserDTO( "Nombre", "user@example.com", "Apellido", List.of(new Rol()));;
        User usuarioCreado = new User(EmailValue.of("user@example.com"), 10L, "Nombre", "Apellido", List.of(new Rol()));

        // Simulamos que al crear el usuario, el repositorio devuelve el usuario con un ID asignado.
        when(userRepository.createUser(any(User.class))).thenReturn(usuarioCreado);

        // Ejecución: se llama al método que se desea probar.
        UserDTO resultadoDTO = userService.createUserDTO(entradaDTO);

        // Verificación: comprobamos que el DTO resultante contenga el ID asignado y que se invoque el guardado del email.
        assertNotNull(resultadoDTO);
        assertEquals(10, resultadoDTO.getId());
        assertEquals("user@example.com", resultadoDTO.getEmail());
        verify(emailValueService, times(1)).saveEmailValue(EmailValue.of("user@example.com"));
    }

    @Test
    void testEmailValueListDomains() {
        // Preparación: simulamos una lista de EmailValue con distintos dominios.
        EmailValue email1 = EmailValue.of("user1@example.com");
        EmailValue email2 = EmailValue.of("user2@example.com");
        EmailValue email3 = EmailValue.of("another@domain.com");
        List<EmailValue> listaEmails = asList(email1, email2, email3);

        when(emailValueService.emailValueList()).thenReturn(listaEmails);

        // Ejecución: se invoca el método que extrae los dominios.
        List<String> dominios = userService.emailValueListDomains();

        // Verificación: se comprueba que se hayan extraído dominios únicos.
        // Dado que se toma la subcadena que comienza en "@" se esperan valores como "@example.com" y "@domain.com".
        assertEquals(2, dominios.size());
        assertTrue(dominios.contains("@example.com"));
        assertTrue(dominios.contains("@domain.com"));
    }
}