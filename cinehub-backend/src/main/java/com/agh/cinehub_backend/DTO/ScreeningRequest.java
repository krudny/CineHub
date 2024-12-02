package com.agh.cinehub_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningRequest {
    private String roomName;
    private String movieTitle;
    private LocalDateTime startDate;
    private float price;
}
