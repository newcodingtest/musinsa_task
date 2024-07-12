package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.domain.Outer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="OUTER", indexes = @Index(name = "idx_outer_price", columnList = "price"))
@Entity
public class OuterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public OuterEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static OuterEntity fromModel(Outer outer){
        return OuterEntity.builder()
                .brand(outer.getBrand())
                .price(outer.getPrice())
                .build();
    }

    public Outer toModel(){
        return Outer.builder()
                .brand(brand)
                .price(price)
                .build();
    }
}