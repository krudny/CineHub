package com.agh.cinehub_backend.DTO;

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
    private String title;
    private String description;
    private Integer duration;
    private String production;
    private String director;
    private LocalDate publishDate;
    private String genre;
}
