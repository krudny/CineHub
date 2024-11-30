package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
