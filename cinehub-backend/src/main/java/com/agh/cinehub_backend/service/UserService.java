package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.RegisterRequest;
import com.agh.cinehub_backend.DTO.UserDto;
import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public void registerUser(RegisterRequest request) {
        Role role = roleService.findByName(request.getRole());

        User newUser = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .build();

        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + newUser.getEmail() + " already exists.");
        }

        userRepository.save(newUser);
    }

    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id: " + id + " does not exist."));
    }

    public UserDto userDtoMapper(User user){
        return new UserDto(user.getUserId(), user.getRole(),
                user.getEmail(), user.getFirstname(), user.getLastname());
    }
}
