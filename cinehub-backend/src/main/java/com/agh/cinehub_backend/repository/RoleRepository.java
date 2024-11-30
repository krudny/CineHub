package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
