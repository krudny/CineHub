package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.MovieRequest;
import com.agh.cinehub_backend.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequest request) {
        movieService.addMovie(request);
        return ResponseEntity.ok("Movie added successfully!");
    }
}
