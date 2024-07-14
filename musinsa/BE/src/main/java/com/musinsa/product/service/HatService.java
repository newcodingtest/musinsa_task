package com.musinsa.product.service;
import com.musinsa.product.domain.Hat;
import com.musinsa.product.infrastructure.entity.HatEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_BRAND_ITEM_NOT_FOUND;
import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class HatService {

    private final HatRepository hatRepository;

    public Hat getHatMinimumPrice(){
        return hatRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(HatEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Hat"));
    }

    public Hat getHatMaximumPrice(){
        return hatRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(HatEntity::toModel)
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Hat"));
    }

    public Hat getHatMinimumPriceByBrand(String brand){
        return hatRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(HatEntity::toModel)
                .orElseThrow(() -> new CategoryException.BrandCategoryItemNotFoundException(CATEGORY_BRAND_ITEM_NOT_FOUND, brand));
    }
}
