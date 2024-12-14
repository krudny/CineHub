package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.DTO.MovieRequest;
import com.agh.cinehub_backend.DTO.ReviewRequest;
import com.agh.cinehub_backend.DTO.ScreeningRequest;
import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.model.*;
import com.agh.cinehub_backend.service.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final GenreService genreService;
    private final ScreeningService screeningService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping
    public List<Movie> getMovies(@RequestParam(value = "genreName", required = false) String genreName) {
        if (genreName != null) {
            Genre genre = genreService.findByName(genreName);
            return movieService.getMoviesByGenre(genre);
        }

        return movieService.getAllMovies();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> addMovie(@Valid @RequestBody MovieRequest request, BindingResult bindingResult) {
        // TODO: check if user have permissions

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        movieService.addMovie(request);
        return ResponseEntity.ok("Movie added successfully!");
    }

    @GetMapping("/{movieId}/screenings") // TODO: any filters?
    public List<Screening> getScreenings(@PathVariable int movieId) {
        Movie movie = movieService.getMovieById(movieId);

        return screeningService.getScreeningsByMovie(movie);
    }

    @PostMapping("/{movieId}/screenings")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> addScreening(@PathVariable int movieId, @Valid @RequestBody ScreeningRequest request, BindingResult bindingResult) {
        // TODO: check if user have permissions

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        Movie movie = movieService.getMovieById(movieId);
        screeningService.addScreening(movie, request);

        return ResponseEntity.ok("Screening for film " + movie.getTitle() + " added successfully!");
    }

    @GetMapping("/{movieId}/screenings/{screeningId}/tickets")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public List<Ticket> getTickets(@PathVariable int movieId, @PathVariable int screeningId) {
        Screening screening = screeningService.getScreeningById(screeningId);
        return ticketService.getTicketsByScreening(screening);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    @PostMapping("/{movieId}/screenings/{screeningId}/tickets")
    public ResponseEntity<?> addTicket(@PathVariable int movieId, @PathVariable int screeningId, @Valid @RequestBody List<TicketRequest> requests, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(userEmail);
        Movie movie = movieService.getMovieById(movieId);
        Screening screening = screeningService.getScreeningById(screeningId);
        requests.forEach(request -> {
            ticketService.addTicket(user, screening, request);
        });

        return ResponseEntity.ok("Ticket for film " + movie.getTitle() + " added successfully!");
    }

    @GetMapping("/{movieId}/reviews")
    public List<Review> getReviews(@PathVariable int movieId) {
        Movie movie = movieService.getMovieById(movieId);

        return reviewService.getReviewsByMovie(movie);
    }

    @PostMapping("/{movieId}/reviews")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE', 'USER')")
    public ResponseEntity<?> addReview(@PathVariable int movieId, @Valid @RequestBody ReviewRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        // TODO: check if user is logged in and get requesting user
        int userId = 1;
        User user = userService.getUserById(userId);
        Movie movie = movieService.getMovieById(movieId);

        reviewService.addReview(user, movie, request);

        return ResponseEntity.ok("Review for film " + movie.getTitle() + " added successfully!");
    }


}
