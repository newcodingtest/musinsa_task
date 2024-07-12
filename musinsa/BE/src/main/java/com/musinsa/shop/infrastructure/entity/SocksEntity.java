package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.domain.Socks;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="SOCKS", indexes = @Index(name = "idx_socks_price", columnList = "price"))
@Entity
public class SocksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public SocksEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static SocksEntity fromModel(Socks socks){
        return SocksEntity.builder()
                .brand(socks.getBrand())
                .price(socks.getPrice())
                .build();
    }

    public Socks toModel(){
        return Socks.builder()
                .brand(brand)
                .price(price)
                .build();
    }

}
