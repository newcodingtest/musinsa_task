package com.musinsa.shop.api;


import com.musinsa.shop.api.response.LowestPricedResponse;
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

    @GetMapping("/category/lowest-price")
    public ResponseEntity<LowestPricedResponse> findLowestPricedProductsByCategory(){

        List<ProductQueryDto> items = productQueryService.getLowerItems();

        BigDecimal totalPrice = items.stream()
                .map(ProductQueryDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LowestPricedResponse response = LowestPricedResponse.builder()
                .items(items.stream()
                        .map(LowestPricedResponse.LowerPricedItem::fromModel)
                        .collect(Collectors.toList()))
                .totalPrice(totalPrice.toString())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
     * */

    @GetMapping("/brand/lowest-price")
    public ResponseEntity<LowestPricedResponse> findLowestPricedProductsByBrand(){
        List<ProductQueryDto> lowerBrandItems = productQueryService.getLowerBrandItems();

        BigDecimal totalPrice = lowerBrandItems.stream()
                .map(ProductQueryDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LowestPricedResponse response = LowestPricedResponse.builder()
                .items(lowerBrandItems.stream()
                        .map(LowestPricedResponse.LowerPricedItem::fromModel)
                        .collect(Collectors.toList()))
                .totalPrice(totalPrice.toString())
                .build();

        return new ResponseEntity<>(null, HttpStatus.OK);
    }




}
