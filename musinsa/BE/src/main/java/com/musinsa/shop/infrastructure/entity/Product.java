package com.musinsa.shop.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@MappedSuperclass
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;
}
