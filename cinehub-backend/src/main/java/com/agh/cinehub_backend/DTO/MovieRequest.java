package com.agh.cinehub_backend.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotNull(message = "Duration cannot be empty")
    @Positive(message = "Duration must be greater than 0")
    private Integer duration;

    @NotBlank(message = "Production cannot be empty")
    private String production;

    @NotBlank(message = "Director cannot be empty")
    private String director;

    @NotNull(message = "Publish date cannot be empty")
    private LocalDate publishDate;

    @NotBlank(message = "Genre name cannot be empty")
    private String genreName;
}
