package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findAllByMovie(Movie movie);
}
