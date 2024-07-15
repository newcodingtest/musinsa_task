package com.musinsa.search.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.search.domain.Product;
import com.musinsa.common.utils.BigDecimalUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/***
 *  단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
 *  조회하는 RESPONSE
 */


@Builder
@ToString
@Getter
public class BrandLowestPriceResponse {

    @JsonProperty("최저가")
    LowestProduct lowestProducts;

    @JsonProperty("총액")
    String totalPrice;

    @Getter
    @Builder
     public static class LowestProduct {
        @JsonProperty("브랜드")
        public String brand;

        @JsonProperty("카테고리")
        public List<CategoryPrice> categoryPrices;

    }

    @Getter
    @Builder
    public static class CategoryPrice {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("가격")
        private String price;


        public static CategoryPrice fromModel(Product product){
            return CategoryPrice
                    .builder()
                    .category(product.getCategory())
                    .price(BigDecimalUtils.formatWithCommas(product.getPrice()))
                    .build();
        }
    }
}
