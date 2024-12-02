package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre findByName(String name) {
        return genreRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Genre with name '" + name + "' doesn't exist."));
    }

    public void addGenre(String name) {
        Genre newGenre = Genre.builder()
                .genre(name)
                .build();

        try {
            genreRepository.save(newGenre);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Genre with name '" + name + "' already exists.");
        }
    }
}
