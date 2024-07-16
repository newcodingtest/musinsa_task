package com.musinsa.search.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.search.domain.SearchProduct;
import com.musinsa.common.utils.BigDecimalUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class CategoryOneLowestPriceResponse {
    @JsonProperty("카테고리")
    private String category;
    @JsonProperty("최저가")
    private DetailInfo lowest;
    @JsonProperty("최고가")
    private DetailInfo highest;


    public static DetailInfo fromModel(SearchProduct searchProduct){
        return DetailInfo.builder()
                .brand(searchProduct.getBrand())
                .price(BigDecimalUtils.formatWithCommas(searchProduct.getPrice()))
                .build();
    }

    @Getter
    @ToString
    @Builder
    public static class DetailInfo{
        @JsonProperty("브랜드")
        String brand;
        @JsonProperty("가격")
        String price;

        public DetailInfo(String brand, String price){
            this.brand = brand;
            this.price = price;

        }
    }
}
