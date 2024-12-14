package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {

    @PositiveOrZero(message = "Value must be at least 0")
    @Max(value = 5, message = "Value must be at most 5")
    private Integer score;

    @NotBlank(message = "Description cannot be empty")
    private String description;
}
