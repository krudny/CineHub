package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.RegisterRequest;
import com.agh.cinehub_backend.DTO.UserDto;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.UserRepository;
import com.agh.cinehub_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public Page<UserDto> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.map(userService::userDtoMapper);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public UserDto getUserById(@PathVariable Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id" + id + "not found"));

        return userService.userDtoMapper(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Registered successfully.");
    }

    @PostMapping("/login-success")
    public ResponseEntity<String> loginSuccess() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/login-failure")
    public ResponseEntity<String> loginFailure() {
        return ResponseEntity.badRequest().body("nie ok");
    }

    @GetMapping("/verifySession")
    public ResponseEntity<String> verifySession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = authorities.iterator().next().getAuthority();
            return ResponseEntity.ok(role);
        }

        return ResponseEntity.internalServerError().body("Authentication failed");
    }
}
