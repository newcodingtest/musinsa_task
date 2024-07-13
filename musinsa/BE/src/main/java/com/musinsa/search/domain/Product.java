package com.musinsa.search.domain;

import com.musinsa.shop.domain.Accessory;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class Product {
    private String category;
    private String brand;
    private BigDecimal price;
}
