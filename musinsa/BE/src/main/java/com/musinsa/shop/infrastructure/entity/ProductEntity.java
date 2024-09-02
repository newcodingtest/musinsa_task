package com.musinsa.shop.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //private String category;
    @Enumerated(EnumType.STRING)
    private ClothingCategory category;

    private String brand;

    private BigDecimal price;

    @Builder
    public ProductEntity(ClothingCategory category, String brand, BigDecimal price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }
}
