package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.RoleRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfigurator {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserConfigurator(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            createUser("user", "user", "user@user.user", "user", "USER");
            createUser("admin", "admin", "admin@admin.admin", "admin", "ADMIN");
        }
    }

    private void createUser(String firstName, String lastName, String email, String password, String roleName) {
        Role role = roleRepository.findByName(roleName).orElse(null);

        if (role == null) return;

        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userRepository.save(user);
    }
}
