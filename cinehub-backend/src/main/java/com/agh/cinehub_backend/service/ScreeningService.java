package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.ScreeningRequest;
import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Room;
import com.agh.cinehub_backend.model.Screening;
import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.RoomRepository;
import com.agh.cinehub_backend.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    public void addScreening(ScreeningRequest request) {
        Movie movie = movieRepository.findByTitle(request.getMovieTitle()).orElseThrow(() -> new IllegalArgumentException("Movie not found"));
        Room room = roomRepository.findByName(request.getRoomName()).orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if(request.getStartDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Screening start date cannot be in the past");
        }

        Screening screening = Screening.builder()
                .room(room)
                .movie(movie)
                .startDate(request.getStartDate())
                .price(request.getPrice())
                .build();

        // TODO: any error handling?
        screeningRepository.save(screening);

    }
}
