package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query("SELECT g from Genre g WHERE g.genre = :name")
    Optional<Genre> findByName(String name);
}
