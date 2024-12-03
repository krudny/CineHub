package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotBlank(message = "Discount name cannot be empty")
    private String discountName;

    // TODO: Probably that should be changed
    private Integer seatId;
}
