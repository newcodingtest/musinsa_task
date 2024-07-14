package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Bag;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BagService {
    private final BagRepository bagRepository;

    public Bag getBagMinimumPrice(){
        return bagRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(BagEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Bag getBagMaximumPrice(){
        return bagRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(BagEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Bag getBagMinimumPriceByBrand(String brand){
        return bagRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(BagEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
