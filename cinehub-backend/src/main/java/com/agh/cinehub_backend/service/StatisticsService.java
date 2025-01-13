package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.TicketRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import com.agh.cinehub_backend.service.StatisticsQuartz.StatisticsStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final StatisticsStorage statisticsStorage;


    public Map<Movie, Long> getMostPopularMoviesMap(){
        return statisticsStorage.getMostPopularMoviesMap();
    }
    public List<Movie> getMostPopularMoviesList(){
        return statisticsStorage.getMostPopularMoviesList();
    }

}
