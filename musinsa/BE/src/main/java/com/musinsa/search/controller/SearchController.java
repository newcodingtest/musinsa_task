package com.musinsa.search.controller;

import com.musinsa.search.api.response.BrandLowestPriceResponse;
import com.musinsa.search.api.response.CategoryLowestPriceResponse;
import com.musinsa.search.api.response.CategoryOneLowestPriceResponse;
import com.musinsa.search.facade.PricingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/search")
@RequiredArgsConstructor
@RestController
@ResponseBody
public class SearchController {
    private final PricingFacade pricingFacade;


    /**
     * 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 페이지
     *
     * */
    @GetMapping("/cheapest-category")
    public ResponseEntity<CategoryLowestPriceResponse> getLowestPriceCategoryProduct(){
        return new ResponseEntity<>(pricingFacade.getMinimumProduct(),
                HttpStatus.FOUND);
    }

    /**
     *
     * 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
     * 조회하는 페이지
     * */
    @GetMapping("/cheapest-brand")
    public ResponseEntity<BrandLowestPriceResponse> getLowestPriceBrandProduct(){
        return new ResponseEntity<>(pricingFacade.getLowestBrandProduct(),
                HttpStatus.FOUND);
    }

    /**
     *  3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 페이지
     *
     * */
    @GetMapping("/cheapest-hightest/{category}")
    public ResponseEntity<CategoryOneLowestPriceResponse> getLowestPriceBrandProduct(@PathVariable final String category){
        return new ResponseEntity<>(pricingFacade.getLowHigtestBrandPrice(category),
                HttpStatus.FOUND);
    }
}