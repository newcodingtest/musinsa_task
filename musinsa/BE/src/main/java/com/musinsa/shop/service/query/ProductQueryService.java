package com.musinsa.shop.service.query;


import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductQueryService {

    private final ProductRepository productRepository;

    public List<ProductQueryDto> getLowerItems(){
        List<ProductQueryDto> items =  productRepository.findCategoryLowestPriceDetails();
        return items;
    }

}
