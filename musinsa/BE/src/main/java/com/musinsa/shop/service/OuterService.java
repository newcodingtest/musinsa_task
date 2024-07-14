package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OuterService {

    private final OuterRepository outerRepository;

    public Outer getOuterMinimumPrice(){
        return outerRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Outer getOuterMaximumPrice(){
        return outerRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Outer getOuterMinimumPriceByBrand(String brand){
        return outerRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }


}
