package com.musinsa.product.service;


import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Bottom;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.BottomEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.musinsa.common.exception.CategoryException;
import org.springframework.transaction.annotation.Transactional;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;
@RequiredArgsConstructor
@Service
public class BottomService {

    private final BottomRepository bottomRepository;

    public Bottom getBottomMinimumPrice(){
        return bottomRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Bottom"));
    }

    public Bottom getBottomMaximumPrice(){
        return bottomRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Bottom"));
    }

    public Bottom getBottomMinimumPriceByBrand(String brand){
        return bottomRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }

    public void createBottom(Bottom bottom){
        bottomRepository.save(BottomEntity
                .fromModel(bottom));
    }

    @Transactional
    public void updateBottom(Long id, Bottom bottom){
        BottomEntity bottomEntity = bottomRepository.findById(id).get();
        bottomEntity.change(bottom);
    }

    public void deleteBottom(Long id){
        bottomRepository.deleteById(id);
    }
}
