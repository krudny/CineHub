package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query("SELECT s FROM Status s WHERE s.name = :name")
    Optional<Status> findByName(String name);
}
