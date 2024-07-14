package com.musinsa.product.domain;

import com.musinsa.search.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
public class Bag {
    String brand;
    BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("가방")
                .brand(brand)
                .price(price)
                .build();
    }
}
