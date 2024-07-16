package com.musinsa.product.service;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Hat;
import com.musinsa.product.domain.Outer;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.HatEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.musinsa.common.exception.CategoryException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public void createHat(Hat hat){
        hatRepository.save(HatEntity
                .fromModel(hat));
    }

    @Transactional
    public void updateHat(Long id, Hat accessory){
        HatEntity hatEntity = hatRepository.findById(id).get();
        hatEntity.change(accessory);
    }

    public void deleteHat(Long id){
        hatRepository.deleteById(id);
    }

    public List<Hat> getAll(){
        return hatRepository.findAll()
                .stream().map(HatEntity::toModel)
                .collect(Collectors.toList());
    }

    public Hat findOne(Long id) {
        return hatRepository.findById(id).get().toModel();
    }
}
