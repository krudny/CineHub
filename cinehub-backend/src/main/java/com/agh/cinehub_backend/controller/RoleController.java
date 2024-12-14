package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.RoleRequest;
import com.agh.cinehub_backend.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleRequest request) {
        roleService.addRole(request);
        return ResponseEntity.ok("Role '" + request.getName() + "' added successfully.");
    }
}
