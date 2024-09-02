package com.musinsa.shop.api.response;

import com.musinsa.shop.dto.query.ProductQueryDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemResponse {

    /**
     * 카테고리
     * */
    public String category;
    /**
     * 브랜드
     * */
    public String brand;
    /**
     * 가격
     * */
    public String price;


    public static ItemResponse fromModel(ProductQueryDto productQueryDto){
        return ItemResponse.builder()
                .category(productQueryDto.getCategory().name())
                .brand(productQueryDto.getBrand())
                .price(productQueryDto.getPrice().toString())
                .build();
    }

}
