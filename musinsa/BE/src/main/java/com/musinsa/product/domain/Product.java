package com.musinsa.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
public class Product{
    Long id;
    String brand;
    String category;
    BigDecimal price;


    public static Product toModel(Accessory accessory){
        return Product.builder()
                .id(accessory.getId())
                .brand(accessory.getBrand())
                .category("액세서리")
                .price(accessory.getPrice())
                .build();
    }

    public static Product toModel(Bag bag){
        return Product.builder()
                .id(bag.getId())
                .brand(bag.getBrand())
                .category("가방")
                .price(bag.getPrice())
                .build();
    }

    public static Product toModel(Bottom bottom){
        return Product.builder()
                .id(bottom.getId())
                .brand(bottom.getBrand())
                .category("하의")
                .price(bottom.getPrice())
                .build();
    }

    public static Product toModel(Hat hat){
        return Product.builder()
                .id(hat.getId())
                .brand(hat.getBrand())
                .category("모자")
                .price(hat.getPrice())
                .build();
    }

    public static Product toModel(Outer outer){
        return Product.builder()
                .id(outer.getId())
                .brand(outer.getBrand())
                .category("아우터")
                .price(outer.getPrice())
                .build();
    }

    public static Product toModel(Sneakers sneakers){
        return Product.builder()
                .id(sneakers.getId())
                .brand(sneakers.getBrand())
                .category("스니커즈")
                .price(sneakers.getPrice())
                .build();
    }

    public static Product toModel(Socks socks){
        return Product.builder()
                .id(socks.getId())
                .brand(socks.getBrand())
                .category("양말")
                .price(socks.getPrice())
                .build();
    }

    public static Product toModel(Top top){
        return Product.builder()
                .id(top.getId())
                .brand(top.getBrand())
                .category("상의")
                .price(top.getPrice())
                .build();
    }
}
