package com.musinsa.product.domain;

import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.search.domain.SearchProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
public class Socks {
    public Long id;
    String brand;
    BigDecimal price;

    public SearchProduct toModel(){
        return SearchProduct.builder()
                .category("양말")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Socks create(ProductCreateRequest productCreateRequest) {
        return Socks.builder()
                .brand(productCreateRequest.getBrand())
                .price(productCreateRequest.getPrice())
                .build();
    }

    public static Socks update(ProductUpdateRequest productUpdateRequest) {
        return Socks.builder()
                .brand(productUpdateRequest.getBrand())
                .price(productUpdateRequest.getPrice())
                .build();
    }
}
