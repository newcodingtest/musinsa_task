package com.musinsa.search.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.search.domain.Product;
import com.musinsa.search.utils.BigDecimalUtils;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 RESPONSE
 *
 * */

@ToString
@Getter
@Builder
public class CategoryLowestPriceResponse {
    List<CategoryLowestProduct> products;
    @JsonProperty("총액")
    String totalPrice;

    @Getter
    @AllArgsConstructor
    public static class CategoryLowestProduct {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("브랜드")
        private String brand;
        @JsonProperty("가격")
        private String price;
    }

    public static List<CategoryLowestProduct> fromModel(List<Product> products){
        return products.stream()
                .map(product -> new CategoryLowestProduct(
                        product.getCategory(),
                        product.getBrand(),
                        BigDecimalUtils.formatWithCommas(product.getPrice())))
                .collect(Collectors.toList());
    }
}
