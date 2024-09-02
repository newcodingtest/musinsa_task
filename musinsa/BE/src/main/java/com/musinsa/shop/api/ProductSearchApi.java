package com.musinsa.shop.api;


import com.musinsa.shop.api.response.ItemResponse;
import com.musinsa.shop.api.response.LowestPricedItems;
import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.service.query.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Component
public class ProductSearchApi {

    private final ProductQueryService productQueryService;

    /**
     * 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
     * */

    @GetMapping("/lowest-price")
    public ResponseEntity<LowestPricedItems> findLowestPricedItemsByCategory(){

        List<ProductQueryDto> items = productQueryService.getLowerItems();


        BigDecimal totalPrice = items.stream()
                .map(ProductQueryDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LowestPricedItems response = LowestPricedItems.builder()
                .items(items.stream()
                        .map(ItemResponse::fromModel)
                        .collect(Collectors.toList()))
                .totalPrice(totalPrice.toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
