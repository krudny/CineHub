package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.MovieRequest;
import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.service.GenreService;
import com.agh.cinehub_backend.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final GenreService genreService;

    @GetMapping
    public List<Movie> getMovies(@RequestParam(value = "genreName", required = false) String genreName) {
        if (genreName != null) {
            Genre genre = genreService.findByName(genreName);
            return movieService.getMoviesByGenre(genre);
        }

        return movieService.getAllMovies();
    }

    @GetMapping("/page")
    public Page<Movie> getPagedMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieService.getPagedMovies(pageable);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/trending")
    public List<Movie> getTrendingMovies() {
        return movieService.getTrendingMovies();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest request) {
        movieService.addMovie(request);
        return ResponseEntity.ok("Movie added successfully!");
    }
}
