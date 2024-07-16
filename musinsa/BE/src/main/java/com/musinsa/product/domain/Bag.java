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
public class Bag {
    public Long id;
    String brand;
    BigDecimal price;

    public SearchProduct toModel(){
        return SearchProduct.builder()
                .category("가방")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Bag create(ProductCreateRequest productCreateRequest) {
        return Bag.builder()
                .brand(productCreateRequest.getBrand())
                .price(productCreateRequest.getPrice())
                .build();
    }

    public static Bag update(ProductUpdateRequest productUpdateRequest) {
        return Bag.builder()
                .brand(productUpdateRequest.getBrand())
                .price(productUpdateRequest.getPrice())
                .build();
    }
}
