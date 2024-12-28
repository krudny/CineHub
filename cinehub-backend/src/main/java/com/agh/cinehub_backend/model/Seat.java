package com.agh.cinehub_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seatId;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonBackReference
    private Room room;

    @Column(nullable = false)
    private Integer seatNumber;
}