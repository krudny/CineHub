package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.TicketRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import com.agh.cinehub_backend.service.StatisticsQuartz.StatisticsStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Map<LocalDate, Integer> getSoldTicketsStatistics(Integer movieId) {
        Map<LocalDate, Integer> ticketsByDate = ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getScreening().getMovie().getMovieId().equals(movieId))
                .collect(Collectors.groupingBy(
                        ticket -> ticket.getScreening().getStartDate().toLocalDate(),
                        Collectors.summingInt(ticket -> 1)
                ));

        LocalDate today = LocalDate.now();
        return IntStream.rangeClosed(0, 13)
                .mapToObj(today::minusDays)
                .collect(Collectors.toMap(
                        date -> date,
                        date -> ticketsByDate.getOrDefault(date, 0)
                ));
    }

}
