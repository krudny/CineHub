package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.model.User;
import com.agh.cinehub_backend.repository.RoleRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfigurator {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConfigurator(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {

        if (userRepository.count() == 0) {
            createUser("user", "user", "user@user.user",
                    "user", "USER");

            createUser("admin", "admin", "admin@admin.admin",
                    "admin", "ADMIN");
        }
    }

    private void createUser(String firstName, String lastName, String email, String password, String roleName) {
        Role role = roleRepository.findByName(roleName).orElse(null);

        if (role == null) return;

        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
