package com.musinsa.product.service;

import com.musinsa.product.domain.Outer;
import com.musinsa.product.infrastructure.entity.OuterEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class OuterService {

    private final OuterRepository outerRepository;

    public Outer getOuterMinimumPrice(){
        return outerRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Outer"));
    }

    public Outer getOuterMaximumPrice(){
        return outerRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Outer"));
    }

    public Outer getOuterMinimumPriceByBrand(String brand){
        return outerRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }


}
