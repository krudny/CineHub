package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.model.Seat;
import com.agh.cinehub_backend.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<Seat> getRoomSeats(Integer roomId) {
        return roomRepository.getRoomSeats(roomId);
    }
}
