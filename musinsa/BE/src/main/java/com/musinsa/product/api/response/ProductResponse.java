package com.musinsa.product.api.response;

import com.musinsa.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProductResponse {
    public Long id;
    public String brand;
    public String category;
    public BigDecimal price;


    public static ProductResponse fromModel(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .category(product.getCategory())
                .price(product.getPrice())
                .build();
    }
}
