package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.domain.Sneakers;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="SNEAKERS", indexes = @Index(name = "idx_sneakers_price", columnList = "price"))
@Entity
public class SneakersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public SneakersEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static SneakersEntity fromModel(Sneakers sneakers){
        return SneakersEntity.builder()
                .brand(sneakers.getBrand())
                .price(sneakers.getPrice())
                .build();
    }

    public Sneakers toModel(){
        return Sneakers.builder()
                .brand(brand)
                .price(price)
                .build();
    }
}
