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
            createRoom("101", 10, 12);
            createRoom("102", 5, 8);
            createRoom("103", 15, 20);
        }
    }

    private void createRoom(String roomName, int numberOfRows, int numberOfColumns) {
        int numberOfSeats = numberOfRows * numberOfColumns;

        Room room = new Room();
        room.setName(roomName);
        room.setNumberOfRows(numberOfRows);
        room.setNumberOfSeats(numberOfSeats);
        roomRepository.save(room);

        createSeatsForRoom(room, numberOfRows, numberOfColumns);
    }

    private void createSeatsForRoom(Room room, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Seat seat = new Seat();
                seat.setRoom(room);
                seat.setRow_(row);
                seat.setColumn_(col);
                seatRepository.save(seat);
            }
        }
    }
}
