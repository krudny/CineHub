package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);

    List<Movie> findAllByGenre(Genre genre);
}
