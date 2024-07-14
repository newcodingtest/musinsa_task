package com.musinsa.product.service;

import com.musinsa.product.domain.Socks;
import com.musinsa.product.infrastructure.entity.SocksEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class SocksService {

    private final SocksRepository socksRepository;

    public Socks getSocksMinimumPrice(){
        return socksRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(SocksEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Socks"));
    }

    public Socks getSocksMaximumPrice(){
        return socksRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(SocksEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Socks"));
    }

    public Socks getSocksMinimumPriceByBrand(String brand){
        return socksRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(SocksEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }
}
