package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.GenreRequest;
import com.agh.cinehub_backend.DTO.ScreeningRequest;
import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping
    public ResponseEntity<String> addGenre(@RequestBody GenreRequest request) {
        // TODO: check if user have permissions
        genreService.addGenre(request);
        return ResponseEntity.ok("Genre with name " + request.getGenre() + " added successfully!");
    }
}
