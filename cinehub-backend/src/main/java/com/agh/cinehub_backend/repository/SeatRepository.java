package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
