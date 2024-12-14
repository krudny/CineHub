package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotNull(message = "ScreeningId cannot be empty")
    private Integer screeningId;

    @NotBlank(message = "Discount name cannot be empty")
    private String discountName;

    // TODO: Probably that should be changed
    @NotNull(message = "SeatId cannot be empty")
    private Integer seatId;
}
