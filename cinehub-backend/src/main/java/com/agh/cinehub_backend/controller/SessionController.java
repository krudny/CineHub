package com.agh.cinehub_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/check-auth")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','USER')")
    public ResponseEntity<?> checkAuth() {
        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", true);
        return ResponseEntity.status(200).body(response);
    }

}
