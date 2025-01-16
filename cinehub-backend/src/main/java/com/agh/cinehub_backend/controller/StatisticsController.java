package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/getMostPopularMoviesEver/{quantity}")
    public List<Movie> getMostPopularMoviesEver(@PathVariable Integer quantity){
        if(quantity == null || quantity<1) quantity = 10;
        List<Movie> movies = statisticsService.getMostPopularMoviesWithListSize(quantity);

        return movies.stream().limit(quantity).toList();
    }

    @GetMapping("/soldTickets/{movieId}")
    public Map<LocalDate, Integer> getSoldTicketsStatistics(@PathVariable Integer movieId) {
        return statisticsService.getSoldTicketsStatistics(movieId);
    }

}
