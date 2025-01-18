package com.agh.cinehub_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies_ratings")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "highest_rating")
    private Double highestRating;

    @Column(name = "lowest_rating")
    private Double lowestRating;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "median_rating")
    private Double medianRating;

    @Column(name = "total_ratings", nullable = false)
    private Integer totalRatings;
}
