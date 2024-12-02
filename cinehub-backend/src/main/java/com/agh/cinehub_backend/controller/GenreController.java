package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genre")
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/add")
    public ResponseEntity<String> addGenre(@RequestParam String name) {
        genreService.addGenre(name);
        return ResponseEntity.ok("Genre with name " + name + " added successfully!");
    }
}
