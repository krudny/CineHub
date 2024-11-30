package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.RegisterRequest;
import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public ResponseEntity<String> registerUser(RegisterRequest request) {
        Optional<Role> role = roleService.getRoleByName(request.getRole());

        if(role.isEmpty()){
            return ResponseEntity.badRequest().body("Role " + request.getRole() + " doesn't exists.");
        }

        User new_user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role.get())
                .build();

        if (userRepository.findByEmail(new_user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User with email " + new_user.getEmail() + " already exists.");
        }

        userRepository.save(new_user);
        return ResponseEntity.ok("User added successfully.");
    }
}
