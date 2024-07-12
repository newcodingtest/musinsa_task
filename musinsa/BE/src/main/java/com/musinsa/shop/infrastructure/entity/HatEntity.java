package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.domain.Hat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="HAT", indexes = @Index(name = "idx_hat_price", columnList = "price"))
@Entity
public class HatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public HatEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static HatEntity fromModel(Hat hat){
        return HatEntity.builder()
                .brand(hat.getBrand())
                .price(hat.getPrice())
                .build();
    }

    public Hat toModel(){
        return Hat.builder()
                .brand(brand)
                .price(price)
                .build();
    }

}
