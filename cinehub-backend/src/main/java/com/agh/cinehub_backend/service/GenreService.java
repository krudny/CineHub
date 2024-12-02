package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre findByName(String name) {
        return genreRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Genre with name '" + name + "' doesn't exist."));
    }
}
