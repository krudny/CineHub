package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Room;
import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.RoomRepository;
import com.agh.cinehub_backend.repository.ScreeningRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ScreeningConfigurator {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public ScreeningConfigurator(ScreeningRepository screeningRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @PostConstruct
    public void init() {
        if (screeningRepository.count() == 0) {
            createMultipleScreenings("Inception", "101", BigDecimal.valueOf(20), LocalDate.of(2025, 5, 12));
            createMultipleScreenings("Inception", "101", BigDecimal.valueOf(20), LocalDate.of(2025, 5, 13));
            createMultipleScreenings("Inception", "101", BigDecimal.valueOf(20), LocalDate.of(2025, 5, 14));
        }
    }

    private void createScreening(Movie movie, Room room, BigDecimal price, LocalDateTime startDate) {
        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setRoom(room);
        screening.setPrice(price);
        screening.setStartDate(startDate);
        screeningRepository.save(screening);
    }

    private void createMultipleScreenings(String movieName, String roomName, BigDecimal price, LocalDate date) {
        Movie movie = movieRepository.findByTitle(movieName).orElse(null);
        Room room = roomRepository.findByName(roomName).orElse(null);

        if (movie == null || room == null) return;

        List<LocalDateTime> startDates = List.of(
                LocalDateTime.of(date, LocalTime.of(12, 0)),
                LocalDateTime.of(date, LocalTime.of(15, 0)),
                LocalDateTime.of(date, LocalTime.of(18, 0)),
                LocalDateTime.of(date, LocalTime.of(21, 0))
        );

        startDates.forEach(startDate -> {
            createScreening(movie, room, price, startDate);
        });
    }
}
