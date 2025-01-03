package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketRequest {
    @NotNull(message = "TicketId cannot be empty")
    private Integer ticketId;

    @NotEmpty(message = "Discount name cannot be empty")
    private String discountName;
}
