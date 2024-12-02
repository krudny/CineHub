package com.agh.cinehub_backend.service;

import com.agh.cinehub_backend.DTO.TicketRequest;
import com.agh.cinehub_backend.model.*;
import com.agh.cinehub_backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public void addTicket(TicketRequest ticketRequest) {
        User user = userRepository.findById(ticketRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Discount discount = discountRepository.findByName(ticketRequest.getDiscountName())
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));
        Screening screening = screeningRepository.findById(ticketRequest.getScreeningId())
                .orElseThrow(() -> new IllegalArgumentException("Screening not found"));
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


}
