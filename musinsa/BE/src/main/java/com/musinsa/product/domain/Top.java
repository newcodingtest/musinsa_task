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
public class Top {
    String brand;
    BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("상의")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Top create(ProductCreateRequest productCreateRequest) {
        return Top.builder()
                .brand(productCreateRequest.getBrand())
                .price(productCreateRequest.getPrice())
                .build();
    }

    public static Top update(ProductUpdateRequest productUpdateRequest) {
        return Top.builder()
                .brand(productUpdateRequest.getBrand())
                .price(productUpdateRequest.getPrice())
                .build();
    }
}
