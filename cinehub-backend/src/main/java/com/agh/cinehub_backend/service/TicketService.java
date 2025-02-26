package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.UpdateTicketRequest;
import com.agh.cinehub_backend.DTO.TicketDto;
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

    public Ticket addTicket(User user, TicketRequest ticketRequest) {
        Discount discount = discountRepository.findByName(Discounts.STANDARD.getName())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

        Status status = statusRepository.findByName(Statuses.PENDING.getName())
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
                .filter(ticket -> !Statuses.CANCELED.getName().equals(ticket.getStatus().getName()))
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
        return ticket;
    }

    public Ticket updateTicket(User user, UpdateTicketRequest ticketRequest, String statusName) {
        //TODO check if userId = ticket.userId
        Ticket ticket = ticketRepository.findById(ticketRequest.getTicketId())
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

        if (!ticket.getUser().getUserId().equals(user.getUserId())) {
            throw new IllegalArgumentException("Ticket does not belong to user");
        }

        Discount discount = discountRepository.findByName(ticketRequest.getDiscountName())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));

        Status status = statusRepository.findByName(statusName)
                .orElseThrow(() -> new IllegalArgumentException("Status not found"));

        ticket.setDiscount(discount);
        ticket.setStatus(status);

        ticketRepository.save(ticket);
        return ticket;
    }


    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    public List<Ticket> getTicketsByScreening(Screening screening) {
        return ticketRepository.findAllByScreening(screening);
    }

    public TicketDto ticketDtoMapper(Ticket ticket){
        return new TicketDto(ticket.getTicketId(), ticket.getScreening().getScreeningId(),
                ticket.getSeat().getSeatId(), ticket.getSeat().getSeatNumber(), ticket.getDiscount().getName(), ticket.getScreening().getPrice(), ticket.getDiscount().getValue());
    }
}
