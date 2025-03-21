package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    boolean existsById(Integer id);
    @Query("SELECT g.name FROM Genre g WHERE g.genreId = :id")
    Optional<String> findNameById(Integer id);
}
