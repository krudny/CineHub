package com.agh.cinehub_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private Integer userId;
    private String discountName;
    private Integer screeningId;
    // TODO: Probably that should be changed
    private Integer seatId;
}
