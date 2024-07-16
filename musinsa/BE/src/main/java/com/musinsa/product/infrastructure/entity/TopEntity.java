package com.musinsa.product.infrastructure.entity;

import com.musinsa.product.domain.Socks;
import com.musinsa.product.domain.Top;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="TOP", indexes = @Index(name = "idx_top_price", columnList = "price"))
@Entity
public class TopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public TopEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static TopEntity fromModel(Top top){
        return TopEntity.builder()
                .brand(top.getBrand())
                .price(top.getPrice())
                .build();
    }

    public Top toModel(){
        return Top.builder()
                .id(id)
                .brand(brand)
                .price(price)
                .build();
    }

    public void change(Top top){
        this.price = top.getPrice();
        this.brand = top.getBrand();
    }
}
