package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Role;
import com.agh.cinehub_backend.repository.RoleRepository;
import com.agh.cinehub_backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/add")
    public ResponseEntity<String> addRole(@RequestParam String name) {
        roleService.addRole(name);
        return ResponseEntity.ok("Role '" + name + "' added successfully.");
    }
}
