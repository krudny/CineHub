package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.ScreeningRequest;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.service.MovieService;
import com.agh.cinehub_backend.service.ScreeningService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService screeningService;
    private final MovieService movieService;

    @GetMapping // TODO: any filters?
    public List<Screening> getScreenings(@RequestParam(value = "movieId") int movieId) {
        Movie movie = movieService.getMovieById(movieId);

        return screeningService.getScreeningsByMovie(movie);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> addScreening(@Valid @RequestBody ScreeningRequest request) {
        screeningService.addScreening(request);

        Movie movie = movieService.getMovieById(request.getMovieId());

        return ResponseEntity.ok("Screening for film " + movie.getTitle() + " added successfully!");
    }
}
