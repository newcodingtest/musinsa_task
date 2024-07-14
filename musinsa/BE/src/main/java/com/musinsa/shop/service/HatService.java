package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HatService {

    private final HatRepository hatRepository;

    public Hat getHatMinimumPrice(){
        return hatRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(HatEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Hat getHatMaximumPrice(){
        return hatRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(HatEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Hat getHatMinimumPriceByBrand(String brand){
        return hatRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(HatEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
