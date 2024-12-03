package com.agh.cinehub_backend.controller;


import com.agh.cinehub_backend.dto.UserDto;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.UserRepository;
import com.agh.cinehub_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users")
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(userService::userDtoMapper);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Integer id ) {
       User user = userRepository.findById(id).orElseThrow(
               () -> new RuntimeException("User with id" + id + "not found"));

        return userService.userDtoMapper(user);
    }
}
