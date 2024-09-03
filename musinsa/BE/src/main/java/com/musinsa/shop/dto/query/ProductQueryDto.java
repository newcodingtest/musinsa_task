package com.musinsa.shop.dto.query;


import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductQueryDto {

    private ClothingCategory category;

    private String brand;

    private BigDecimal price;

    public String toString(){
        return "[ category:: "+category.name()+", brand:: "+brand+", price:: "+price+" ]";
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LowerTotalPriceDto {
        private String brand;
        private BigDecimal totalPrice;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LowerBrandDto {
        private ClothingCategory category;
        private String brand;
        private BigDecimal price;
    }


    public static ProductQueryDto fromModel(LowerBrandDto lowerBrandDto){
        return ProductQueryDto.builder()
                .category(lowerBrandDto.getCategory())
                .brand(lowerBrandDto.getBrand())
                .price(lowerBrandDto.getPrice())
                .build();
    }



}
