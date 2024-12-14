package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class StatisticsService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
}
