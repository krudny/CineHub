package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
