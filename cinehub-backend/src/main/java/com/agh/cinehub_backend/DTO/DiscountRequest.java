package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountRequest {
    @NotBlank(message = "Discount name cannot be empty")
    private String name;

    @PositiveOrZero(message = "Value must be at least 0")
    @Max(value = 1, message = "Value must be at most 1")
    private float value;
}
