package com.musinsa.search.domain;

import com.musinsa.product.domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@Builder
public class SearchProduct {
    private String category;
    private String brand;
    private BigDecimal price;
    
    
    public static SearchProduct fromModel(Accessory accessory){
        return SearchProduct.builder()
                .category("액세서리")
                .brand(accessory.getBrand())
                .price(accessory.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Bag bag){
        return SearchProduct.builder()
                .category("가방")
                .brand(bag.getBrand())
                .price(bag.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Bottom bottom){
        return SearchProduct.builder()
                .category("바지")
                .brand(bottom.getBrand())
                .price(bottom.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Hat hat){
        return SearchProduct.builder()
                .category("모자")
                .brand(hat.getBrand())
                .price(hat.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Outer outer){
        return SearchProduct.builder()
                .category("아우터")
                .brand(outer.getBrand())
                .price(outer.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Sneakers sneakers){
        return SearchProduct.builder()
                .category("스니커즈")
                .brand(sneakers.getBrand())
                .price(sneakers.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Socks socks){
        return SearchProduct.builder()
                .category("양말")
                .brand(socks.getBrand())
                .price(socks.getPrice())
                .build();
    }
    public static SearchProduct fromModel(Top top){
        return SearchProduct.builder()
                .category("상의")
                .brand(top.getBrand())
                .price(top.getPrice())
                .build();
    }
}
