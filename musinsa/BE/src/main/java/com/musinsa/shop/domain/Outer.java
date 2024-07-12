package com.musinsa.shop.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Outer {
    String brand;
    BigDecimal price;
}
