package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> getRoleByName(String name){
        return roleRepository.findByName(name);
    }
}
