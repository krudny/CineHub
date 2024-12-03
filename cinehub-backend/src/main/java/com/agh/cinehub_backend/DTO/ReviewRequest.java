package com.agh.cinehub_backend.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private Integer score;
    private String description;
}
