package com.musinsa.shop.dto.query;


import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

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



}
