package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/getMostPopularMoviesEver/{quantity}")
    public List<Movie> getMostPopularMoviesEver(@PathVariable Integer quantity){
        List<Movie> movies = statisticsService.getMostPopularMoviesList();
        if(quantity == null || quantity<1) quantity = 10;

        return movies.stream().limit(quantity).toList();

    }

}
