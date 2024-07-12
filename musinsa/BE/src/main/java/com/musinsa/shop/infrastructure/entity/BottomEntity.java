package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Bag;
import com.musinsa.shop.domain.Bottom;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="BOTTOM", indexes = @Index(name = "idx_bottom_price", columnList = "price"))
@Entity
public class BottomEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public BottomEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static BottomEntity fromModel(Bottom bottom){
        return BottomEntity.builder()
                .brand(bottom.getBrand())
                .price(bottom.getPrice())
                .build();
    }

    public Bottom toModel(){
        return Bottom.builder()
                .brand(brand)
                .price(price)
                .build();
    }

}
