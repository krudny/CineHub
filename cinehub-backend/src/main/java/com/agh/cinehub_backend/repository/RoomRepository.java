package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
