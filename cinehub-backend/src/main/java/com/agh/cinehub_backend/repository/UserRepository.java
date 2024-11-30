package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
