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
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 23));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 24));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 25));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 26));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 27));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 28));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 29));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 30));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2024, 12, 31));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 1));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 2));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 3));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 4));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 5));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 6));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 7));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 8));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 9));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 10));
            createMultipleScreenings("Carry-On", "101", 20, LocalDate.of(2025, 1, 11));
        }
    }

    private void createScreening(Movie movie, Room room, float price, LocalDateTime startDate) {
        Screening screening = new Screening();
        screening.setMovie(movie);
        screening.setRoom(room);
        screening.setPrice(price);
        screening.setStartDate(startDate);
        screeningRepository.save(screening);
    }

    private void createMultipleScreenings(String movieName, String roomName, float price, LocalDate date) {
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
