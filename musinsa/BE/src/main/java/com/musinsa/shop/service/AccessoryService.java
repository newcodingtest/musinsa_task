package com.musinsa.shop.service;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;

    public Accessory getAccessoryMinimumPrice(){
        return accessoryRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Accessory"));
    }

    public Accessory getAccessoryMaximumPrice(){
        return accessoryRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Accessory"));
    }

    public Accessory getAccessoryMinimumPriceByBrand(String brand){
        return accessoryRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }

}
