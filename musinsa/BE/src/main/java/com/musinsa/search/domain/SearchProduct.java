package com.musinsa.search.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class SearchProduct {
    private String category;
    private String brand;
    private BigDecimal price;
}
