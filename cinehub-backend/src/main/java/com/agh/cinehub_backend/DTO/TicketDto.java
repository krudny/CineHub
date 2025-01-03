package com.agh.cinehub_backend.DTO;

public record TicketDto(Integer ticketId, Integer screeningId, Integer seatId, Integer seatNumber, String discountName, float basePrice, float discountValue) {
}
