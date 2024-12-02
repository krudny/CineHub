package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenreConfigurator {
    private final GenreRepository genreRepository;

    public GenreConfigurator(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @PostConstruct
    public void init() {
        if (genreRepository.count() == 0) {
            createGenre("Action");
            createGenre("Sci-Fi");
        }
    }

    private void createGenre(String genreName) {
        Genre genre = new Genre();
        genre.setGenre(genreName);
        genreRepository.save(genre);
    }
}
