package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.GenreRequest;
import com.agh.cinehub_backend.DTO.ScreeningRequest;
import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.service.GenreService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> addGenre(@Valid @RequestBody GenreRequest request, BindingResult bindingResult) {
        // TODO: check if user have permissions

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        genreService.addGenre(request);
        return ResponseEntity.ok("Genre with name " + request.getGenre() + " added successfully!");
    }
}
