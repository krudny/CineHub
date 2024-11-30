package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
