package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Bag;
import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BottomService {

    private final BottomRepository bottomRepository;

    public Bottom getBottomMinimumPrice(){
        return bottomRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Bottom getBottomMaximumPrice(){
        return bottomRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Bottom getBottomMinimumPriceByBrand(String brand){
        return bottomRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
