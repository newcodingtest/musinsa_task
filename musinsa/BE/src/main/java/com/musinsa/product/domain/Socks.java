package com.musinsa.product.domain;

import com.musinsa.common.utils.BigDecimalUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.search.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Builder
@Getter
public class Socks {
    String brand;
    BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("양말")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Socks create(ProductCreateRequest productCreateRequest) {
        return Socks.builder()
                .brand(productCreateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productCreateRequest.getPrice()))
                .build();
    }

    public static Socks update(ProductUpdateRequest productUpdateRequest) {
        return Socks.builder()
                .brand(productUpdateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productUpdateRequest.getPrice()))
                .build();
    }
}
