package com.musinsa.shop.service;

import com.musinsa.shop.domain.Sneakers;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class SneakersService {
    private final SneakersRepository sneakersRepository;

    public Sneakers getSneakersMinimumPrice(){
        return sneakersRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Sneakers"));
    }

    public Sneakers getSneakersMaximumPrice(){
        return sneakersRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Sneakers"));
    }
    public Sneakers getSneakersMinimumPriceByBrand(String brand){
        return sneakersRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }
}
