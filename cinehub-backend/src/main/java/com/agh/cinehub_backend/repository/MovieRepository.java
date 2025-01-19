package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Genre;
import com.agh.cinehub_backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByMovieId(int id);

    List<Movie> findAllByGenre(Genre genre);

    @Query("SELECT m.movieId FROM Movie m")
    List<Integer> getMoviesId();

    Page<Movie> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
