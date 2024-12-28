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

//    @PostConstruct
//    public void init() {
//        if (roomRepository.count() == 0) {
//            createRoom("101", 10, 12);
//            createRoom("102", 5, 8);
//            createRoom("103", 15, 20);
//        }
//    }

//    private void createRoom(String roomName, int numberOfRows, int numberOfColumns) {
//        int numberOfSeats = numberOfRows * numberOfColumns;
//
//        Room room = new Room();
//        room.setName(roomName);
//        roomRepository.save(room);
//
//        createSeatsForRoom(room, numberOfRows, numberOfColumns);
//    }

//    private void createSeatsForRoom(Room room, int numberOfRows, int numberOfSeats) {
//        for (int rowNumber = 0; rowNumber < numberOfRows; rowNumber++) {
//            for (int seatNumber = 0; seatNumber < numberOfSeats; seatNumber++) {
//                Seat seat = new Seat();
//                seat.setRoom(room);
//                seat.setRowNumber(rowNumber);
//                seat.setSeatNumber(seatNumber);
//                seatRepository.save(seat);
//            }
//        }
//    }
}
