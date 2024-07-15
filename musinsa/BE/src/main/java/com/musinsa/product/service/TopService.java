package com.musinsa.product.service;

import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Top;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.TopEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import org.springframework.transaction.annotation.Transactional;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class TopService {

    private final TopRepository topRepository;

    public Top getTopMinimumPrice(){
        return topRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(TopEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Top"));
    }

    public Top getTopMaximumPrice(){
        return topRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(TopEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Top"));
    }

    public Top getTopMinimumPriceByBrand(String brand){
        return topRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(TopEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }

    public void createTop(Top top){
        topRepository.save(TopEntity
                .fromModel(top));
    }

    @Transactional
    public void updateTop(Long id, Top top){
        TopEntity topEntity = topRepository.findById(id).get();
        topEntity.change(top);
    }

    public void deleteTop(Long id){
        topRepository.deleteById(id);
    }

}
