package com.agh.cinehub_backend.controller;


import com.agh.cinehub_backend.service.ScreeningService;
import com.agh.cinehub_backend.DTO.ScreeningRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/screening")
public class ScreeningController {
    private final ScreeningService screeningService;

    @PostMapping("/add")
    public ResponseEntity<String> addScreening(@RequestBody ScreeningRequest request) {
        screeningService.addScreening(request);
        return ResponseEntity.ok("Screening for film " + request.getMovieTitle() + " added successfully!");
    }
}
