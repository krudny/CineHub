package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull(message = "MovieId cannot be empty")
    private Integer movieId;

    @NotBlank(message = "Room name cannot be empty")
    private String roomName;

    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDateTime startDate;

    @Positive(message = "Price must be greater than 0")
    private float price;
}
