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
public class Bottom {
    String brand;
    BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("바지")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Bottom create(ProductCreateRequest productCreateRequest) {
        return Bottom.builder()
                .brand(productCreateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productCreateRequest.getPrice()))
                .build();
    }

    public static Bottom update(ProductUpdateRequest productUpdateRequest) {
        return Bottom.builder()
                .brand(productUpdateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productUpdateRequest.getPrice()))
                .build();
    }
}
