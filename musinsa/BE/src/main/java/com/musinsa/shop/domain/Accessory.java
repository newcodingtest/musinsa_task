package com.musinsa.shop.domain;

import com.musinsa.search.domain.Product;
import lombok.*;

import java.math.BigDecimal;


@ToString
@Builder
@Getter
public class Accessory {
    public String brand;
    public BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("액세서리")
                .brand(brand)
                .price(price)
                .build();
    }
}
