package com.dekra.Inicio.user.infraestructure.repository;

import com.dekra.Inicio.user.domain.model.User;
import com.dekra.Inicio.user.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImp implements UserRepository {

    private final Set<User> userRepository= new HashSet<>();
    private Long currentId = 1L;


    @Override
    public List<User> listUsers() {
        return new ArrayList<>(userRepository.stream().toList());
    }

    @Override
    public User createUser(User user) {
         user.setId(currentId++);
         userRepository.add(user);

         return user;
    }

    @Override
    public User updateUser(User user) {
        // Buscar y reemplazar el usuario
        Optional<User> existingUser = userRepository.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst();

        if (existingUser.isPresent()) {
            userRepository.remove(existingUser.get());
            userRepository.add(user);
            return user;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}
