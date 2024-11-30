package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.RegisterRequest;
import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.RoleRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void registerUser(RegisterRequest request) {
        Optional<Role> role = roleRepository.findByName(request.getRole());

        if (role.isEmpty()) {
            throw new IllegalArgumentException("Role " + request.getRole() + " doesn't exist.");
        }

        User newUser = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role.get())
                .build();

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + newUser.getEmail() + " already exists.");
        }

        userRepository.save(newUser);
    }
}
