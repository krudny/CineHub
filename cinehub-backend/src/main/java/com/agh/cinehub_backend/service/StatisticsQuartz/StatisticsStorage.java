package com.agh.cinehub_backend.service.StatisticsQuartz;


import com.agh.cinehub_backend.model.Movie;
import lombok.Data;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Data
public class StatisticsStorage {
    private Map<Movie, Long> mostPopularMoviesMap;
    private List<Movie> mostPopularMoviesList;


    //TODO
    private User mostActiveUser;


    //TODO
    private List<Movie> bestRatingMovies;
}
