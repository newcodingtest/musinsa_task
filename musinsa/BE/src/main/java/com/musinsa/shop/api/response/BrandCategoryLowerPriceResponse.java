package com.musinsa.shop.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.shop.dto.query.ProductQueryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BrandCategoryLowerPriceResponse {

    @JsonProperty("최저가")
    private BrandCategoryLowerPriceItems brandCategoryLowerPriceItems;

    private class BrandCategoryLowerPriceItems {
        @JsonProperty("브랜드")
        String brand;
        @JsonProperty("카테고리")
        private List<BrandCategoryLowerPriceItem> categoryItems;

    }
    private class BrandCategoryLowerPriceItem {
        @JsonProperty("카테고리")
        private String category;
        @JsonProperty("가격")
        private String price;
    }

}
