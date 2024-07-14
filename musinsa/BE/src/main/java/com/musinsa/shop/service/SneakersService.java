package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.domain.Sneakers;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SneakersService {
    private final SneakersRepository sneakersRepository;

    public Sneakers getSneakersMinimumPrice(){
        return sneakersRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

    public Sneakers getSneakersMaximumPrice(){
        return sneakersRepository.findFirstByOrderByPriceDescBrandDesc()
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
    public Sneakers getSneakersMinimumPriceByBrand(String brand){
        return sneakersRepository.findFirstByBrandOrderByPriceAsc(brand)
                .map(SneakersEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
