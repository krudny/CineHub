package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    @Query("SELECT name FROM Discount")
    List<String> getAllDiscountsNames();

    @Query("SELECT d FROM Discount d WHERE d.name = :name")
    Optional<Discount> findByName(String name);
}
