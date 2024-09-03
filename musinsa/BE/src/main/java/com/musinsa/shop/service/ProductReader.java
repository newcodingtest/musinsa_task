package com.musinsa.shop.service;

import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductReader {

    private final ProductRepository productRepository;


    public List<ProductQueryDto> getLowerItemsCatByCat(){
        List<ProductQueryDto> items =  productRepository.findCategoryLowestPriceDetails();
        return items;
    }

    public String getLowerBrand(){
        return productRepository.findLowerBrand().get(0).getBrand();
    }


    public List<ProductQueryDto.LowerBrandDto> getLowerItemsByBrand(final String brand){
        return productRepository.findLowerItem(brand);
    }

}
