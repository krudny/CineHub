package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.RoleRequest;
import com.agh.cinehub_backend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<String> addRole(@RequestBody RoleRequest request) {
        // TODO: check if user have permissions
        roleService.addRole(request);
        return ResponseEntity.ok("Role '" + request.getName() + "' added successfully.");
    }
}
