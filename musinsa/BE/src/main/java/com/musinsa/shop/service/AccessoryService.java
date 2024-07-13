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
        //return accessoryRepository.findFirstByOrderBrandDesc().orElseThrow().toModel();
        return accessoryRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(AccessoryEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

}
