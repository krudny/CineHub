package com.agh.cinehub_backend.service;


import com.agh.cinehub_backend.model.MovieRating;
import com.agh.cinehub_backend.repository.MovieRatingsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRatingsService {
    private final MovieRatingsRepository movieRatingRepository;
    private final MovieRatingsRepository movieRatingsRepository;


    public MovieRatingsService(MovieRatingsRepository movieRatingRepository, MovieRatingsRepository movieRatingsRepository) {
        this.movieRatingRepository = movieRatingRepository;
        this.movieRatingsRepository = movieRatingsRepository;
    }

    public Page<MovieRating> getMovieRatings(Pageable pageable){
        return movieRatingsRepository.getSortedMovieRatings(pageable);
    }

    public List<MovieRating> getMovieRatingsWithLimit(int limit){
        return movieRatingsRepository.getSortedMovieRatingsWithLimit(limit);
    }



}
