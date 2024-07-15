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

    public static Outer create(ProductCreateRequest productCreateRequest) {
        return Outer.builder()
                .brand(productCreateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productCreateRequest.getPrice()))
                .build();
    }

    public static Outer update(ProductUpdateRequest productUpdateRequest) {
        return Outer.builder()
                .brand(productUpdateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productUpdateRequest.getPrice()))
                .build();
    }
}
