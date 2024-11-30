package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
}
