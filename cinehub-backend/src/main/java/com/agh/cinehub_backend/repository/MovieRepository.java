package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
