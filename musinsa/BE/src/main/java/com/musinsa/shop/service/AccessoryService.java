package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;

    public Accessory getAccessoryMinimumPrice(){
        return accessoryRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Accessory getAccessoryMaximumPrice(){
        return accessoryRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Accessory getAccessoryMinimumPriceByBrand(String brand){
        return accessoryRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

}
