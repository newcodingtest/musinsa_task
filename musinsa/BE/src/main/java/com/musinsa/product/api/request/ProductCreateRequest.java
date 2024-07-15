package com.musinsa.product.api.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductCreateRequest {
    /**
     * 브랜드
     * */
    String brand;
    /**
     * 카테고리(모자,양말..)
     * */
    String category;
    /**
     * 가격
     * */
    String price;
}
