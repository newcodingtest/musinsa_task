package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.product.domain.Bag;
import com.musinsa.product.infrastructure.entity.BagEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class BagService {
    private final BagRepository bagRepository;

    public Bag getBagMinimumPrice(){
        return bagRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(BagEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Accessory"));
    }

    public Bag getBagMaximumPrice(){
        return bagRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(BagEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Accessory"));
    }

    public Bag getBagMinimumPriceByBrand(String brand){
        return bagRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(BagEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_NOT_FOUND, brand));
    }
}
