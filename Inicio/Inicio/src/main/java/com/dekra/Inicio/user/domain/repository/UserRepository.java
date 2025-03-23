package com.dekra.Inicio.user.domain.repository;


import com.dekra.Inicio.user.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> listUsers();
    User createUser(User user);
    User updateUser(User user);

}
