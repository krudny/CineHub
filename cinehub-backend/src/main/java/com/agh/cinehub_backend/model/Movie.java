package com.agh.cinehub_backend.model;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(length = 50)
    private String description;

    private Integer duration;

    @Column(length = 30)
    private String production;

    @Column(length = 30)
    private String director;

    private LocalDate publishDate;

}
