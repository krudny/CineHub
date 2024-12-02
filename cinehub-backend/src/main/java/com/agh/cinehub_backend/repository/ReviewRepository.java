package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Review;
import com.agh.cinehub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByUserAndMovie(User user, Movie movie);
}
