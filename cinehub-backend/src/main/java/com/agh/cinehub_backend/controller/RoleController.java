package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.RoleRequest;
import com.agh.cinehub_backend.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleRequest request, BindingResult bindingResult) {
        // TODO: check if user have permissions
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        roleService.addRole(request);
        return ResponseEntity.ok("Role '" + request.getName() + "' added successfully.");
    }
}
