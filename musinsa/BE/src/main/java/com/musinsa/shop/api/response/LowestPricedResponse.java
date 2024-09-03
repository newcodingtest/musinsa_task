package com.musinsa.shop.api.response;

import com.musinsa.shop.dto.query.ProductQueryDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LowestPricedResponse {

    public List<LowerPricedItem> items;

    public String totalPrice;


    @Getter
    @Builder
    public static class LowerPricedItem {

        /**
         * 카테고리
         */
        public String category;
        /**
         * 브랜드
         */
        public String brand;
        /**
         * 가격
         */
        public String price;


        public static LowerPricedItem fromModel(ProductQueryDto productQueryDto) {
            return LowerPricedItem.builder()
                    .category(productQueryDto.getCategory().name())
                    .brand(productQueryDto.getBrand())
                    .price(productQueryDto.getPrice().toString())
                    .build();
        }
    }
}
