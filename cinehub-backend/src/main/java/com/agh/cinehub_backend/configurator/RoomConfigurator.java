package com.agh.cinehub_backend.configurator;

import com.agh.cinehub_backend.model.Room;
import com.agh.cinehub_backend.model.Seat;
import com.agh.cinehub_backend.repository.RoomRepository;
import com.agh.cinehub_backend.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RoomConfigurator {
    private final RoomRepository roomRepository;
    private final SeatRepository seatRepository;

    public RoomConfigurator(RoomRepository roomRepository, SeatRepository seatRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
    }

    @PostConstruct
    public void init() {
        if (roomRepository.count() == 0) {
            createRoom("101", 10);
            createRoom("102", 5);
            createRoom("103", 15);
        }
    }

    private void createRoom(String roomName, int numberOfRows) {
        int numberOfSeats = 5 + 7 + 9 * (numberOfRows-2);

        Room room = new Room();
        room.setName(roomName);
        roomRepository.save(room);

        createSeatsForRoom(room, numberOfSeats);
    }

    private void createSeatsForRoom(Room room, int numberOfSeats) {
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber++) {
            Seat seat = new Seat();
            seat.setRoom(room);
            seat.setSeatNumber(seatNumber);
            seatRepository.save(seat);
        }
    }
}
