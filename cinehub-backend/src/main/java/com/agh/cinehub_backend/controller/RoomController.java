package com.agh.cinehub_backend.controller;

import com.agh.cinehub_backend.model.Seat;
import com.agh.cinehub_backend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/seats/{id}")
    public List<Seat> getRoomSeats(@PathVariable Integer id) {
        return roomService.getRoomSeats(id);
    }

}
