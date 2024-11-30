package com.agh.cinehub_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Data
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountId;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, precision = 3, scale = 2)
    private BigDecimal value;
}

