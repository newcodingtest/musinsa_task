package com.musinsa.product.infrastructure.entity;

import com.musinsa.product.domain.Bottom;
import com.musinsa.product.domain.Hat;
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
                .id(id)
                .brand(brand)
                .price(price)
                .build();
    }

    public void change(Hat hat){
        this.price = hat.getPrice();
        this.brand = hat.getBrand();
    }

}
