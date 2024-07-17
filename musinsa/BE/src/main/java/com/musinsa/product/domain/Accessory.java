package com.musinsa.product.domain;

import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.search.domain.SearchProduct;
import lombok.*;

import java.math.BigDecimal;


@AllArgsConstructor
@ToString
@Builder
@Getter
public class Accessory {
    public Long id;
    public String brand;
    public BigDecimal price;

//    public SearchProduct toModel(){
//        return SearchProduct.builder()
//                .category("액세서리")
//                .brand(brand)
//                .price(price)
//                .build();
//    }

    public static Accessory create(ProductCreateRequest productCreateRequest) {
        return Accessory.builder()
                .brand(productCreateRequest.getBrand())
                .price(productCreateRequest.getPrice())
                .build();
    }

    public static Accessory update(ProductUpdateRequest productUpdateRequest) {
        return Accessory.builder()
                .brand(productUpdateRequest.getBrand())
                .price(productUpdateRequest.getPrice())
                .build();
    }
}
