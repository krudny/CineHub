package com.agh.cinehub_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roomId;

    @Column(nullable = false, length = 30)
    private String name;

    private Integer numberOfSeats;

    private Integer numberOfRows;
}

