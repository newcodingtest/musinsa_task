package com.musinsa.search.domain;

import com.musinsa.shop.domain.Accessory;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class Product {
    private String category;
    private String brand;
    private BigDecimal price;
}
