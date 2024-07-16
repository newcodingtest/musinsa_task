package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Bag;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public void createAccssory(Accessory accessory){
        accessoryRepository.save(AccessoryEntity
                .fromModel(accessory));
    }

    @Transactional
    public void updateAccssory(Long id, Accessory accessory){
        AccessoryEntity accessoryEntity = accessoryRepository.findById(id).get();
        accessoryEntity.change(accessory);
    }

    public void deleteAccssory(Long id){
        accessoryRepository.deleteById(id);
    }

    public List<Accessory> getAll(){
        return accessoryRepository.findAll()
                .stream().map(AccessoryEntity::toModel)
                .collect(Collectors.toList());
    }

    public Accessory findOne(Long id) {
        return accessoryRepository.findById(id).get().toModel();
    }


}
