package com.musinsa.shop.service.query;


import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.jpa.ProductRepository;
import com.musinsa.shop.service.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductQueryService {
    private final ProductReader productReader;


    public List<ProductQueryDto> getLowerItems(){
        List<ProductQueryDto> items =  productReader.getLowerItemsCatByCat();
        return items;
    }

    public List<ProductQueryDto> getLowerBrandItems(){
        String lowerBrand = productReader
                .getLowerBrand();

        List<ProductQueryDto.LowerBrandDto> lowerItems = productReader
                .getLowerItemsByBrand(lowerBrand);

        return lowerItems.stream()
                .map(ProductQueryDto::fromModel)
                .collect(Collectors.toList());
    }

}
