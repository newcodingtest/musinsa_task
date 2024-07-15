package com.musinsa.product.domain;

import com.musinsa.common.utils.BigDecimalUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.search.domain.Product;
import lombok.*;

import java.math.BigDecimal;
import java.text.ParseException;


@AllArgsConstructor
@ToString
@Builder
@Getter
public class Accessory {
    public String brand;
    public BigDecimal price;

    public Product toModel(){
        return Product.builder()
                .category("액세서리")
                .brand(brand)
                .price(price)
                .build();
    }

    public static Accessory create(ProductCreateRequest productCreateRequest) {
        return Accessory.builder()
                .brand(productCreateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productCreateRequest.getPrice()))
                .build();
    }

    public static Accessory update(ProductUpdateRequest productUpdateRequest) {
        return Accessory.builder()
                .brand(productUpdateRequest.getBrand())
                .price(BigDecimalUtils.parseWithoutCommas(productUpdateRequest.getPrice()))
                .build();
    }
}
