package com.musinsa.product.infrastructure.entity;

import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Bag;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="BAG", indexes = @Index(name = "idx_bag_price", columnList = "price"))
@Entity
public class BagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;


    @Builder
    public BagEntity(String brand, BigDecimal price){
        this.brand = brand;
        this.price = price;
    }

    public static BagEntity fromModel(Bag bag){
        return BagEntity.builder()
                .brand(bag.getBrand())
                .price(bag.getPrice())
                .build();
    }

    public Bag toModel(){
        return Bag.builder()
                .id(id)
                .brand(brand)
                .price(price)
                .build();
    }

    public void change(Bag bag){
        this.price = bag.getPrice();
        this.brand = bag.getBrand();
    }

}
