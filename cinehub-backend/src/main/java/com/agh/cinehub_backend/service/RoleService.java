package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.RoleRequest;
import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Role with name '" + name + "' doesn't exist."));
    }

    public void addRole(RoleRequest request) {
        Role newRole = Role.builder()
                .name(request.getName())
                .build();

        try {
            roleRepository.save(newRole);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Role with name '" + request.getName() + "' already exists.");
        }
    }

}
