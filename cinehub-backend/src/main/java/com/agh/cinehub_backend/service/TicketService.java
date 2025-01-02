package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.model.*;
import com.agh.cinehub_backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DiscountRepository discountRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final StatusRepository statusRepository;
    private final RoomRepository roomRepository;

    public void addTicket(User user, TicketRequest ticketRequest) {
        Discount discount = discountRepository.findByName(ticketRequest.getDiscountName())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

        Status status = statusRepository.findByName("Pending")
                .orElseThrow(() -> new IllegalArgumentException("Status not found"));

        Screening screening = screeningRepository.findById(ticketRequest.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("Screening not found"));

        Seat seat = seatRepository.findById(ticketRequest.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        List<Seat> seats = roomRepository.getRoomSeats(screening.getRoom().getRoomId());
        if(!seats.contains(seat)) throw new IllegalArgumentException("Seat is in different room");

        if (screening.getStartDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Screening already started");
        }

        List<Seat> seatsTaken = ticketRepository
                .findByScreeningAndSeat(screening, seat)
                .stream()
                .filter(ticket -> !Statuses.CANCELLED.getName().equals(ticket.getStatus().getName()))
                .map(Ticket::getSeat)
                .toList();
        if (!seatsTaken.isEmpty()) {
            throw new IllegalArgumentException("Seat already taken");
        }

        Ticket ticket = Ticket.builder()
                .user(user)
                .discount(discount)
                .screening(screening)
                .seat(seat)
                .status(status)
                .reservationDate(LocalDateTime.now())
                .build();

        ticketRepository.save(ticket);
    }


    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    public List<Ticket> getTicketsByScreening(Screening screening) {
        return ticketRepository.findAllByScreening(screening);
    }
}
