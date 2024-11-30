package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
