package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.Room;
import com.agh.cinehub_backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByName(String name);
    @Query("SELECT s FROM Seat s WHERE s.room.roomId = :roomId")
    List<Seat> getRoomSeats(@Param("roomId") Integer roomId);

}
