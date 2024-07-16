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
public class Top {
    public Long id;
    String brand;
    BigDecimal price;

    public SearchProduct toModel(){
        return SearchProduct.builder()
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
