package com.musinsa.shop.infrastructure.entity;

import com.musinsa.shop.domain.Accessory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="ACCESSORY", indexes = @Index(name = "idx_accessory_price", columnList = "price"))
@Entity
public class AccessoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public AccessoryEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static AccessoryEntity fromModel(Accessory accessory){
        return AccessoryEntity.builder()
                .brand(accessory.getBrand())
                .price(accessory.getPrice())
                .build();
    }

    public Accessory toModel(){
        return Accessory.builder()
                .brand(brand)
                .price(price)
                .build();
    }
}
