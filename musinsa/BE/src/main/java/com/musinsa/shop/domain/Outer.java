package com.musinsa.shop.domain;

import com.musinsa.search.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
public class Outer {
    String brand;
    BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("아우터")
                .brand(brand)
                .price(price)
                .build();
    }
}
