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
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final StatusRepository statusRepository;

    // TODO: there's no validation if seat is taken and room capacity is not exceeded
    public void addTicket(User user, Screening screening, TicketRequest ticketRequest) {
        Discount discount = discountRepository.findByName(ticketRequest.getDiscountName())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));
        Seat seat = seatRepository.findById(ticketRequest.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
        Status status = statusRepository.findByName("Pending")
                .orElseThrow(() -> new IllegalArgumentException("Status not found"));

        if (screening.getStartDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Screening already started");
        }

        Ticket ticket = Ticket.builder()
                .user(user)
                .discount(discount)
                .screening(screening)
                .seat(seat)
                .status(status)
                .reservationDate(LocalDateTime.now())
                .build();

        // TODO: any error handling?
        ticketRepository.save(ticket);
    }


    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findAllByUser(user);
    }

    public List<Ticket> getTicketsByScreening(Screening screening) {
        return ticketRepository.findAllByScreening(screening);
    }
}
