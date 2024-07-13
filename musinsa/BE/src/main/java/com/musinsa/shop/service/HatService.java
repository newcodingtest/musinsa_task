package com.musinsa.shop.service;

import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HatService {

    private final HatRepository hatRepository;

    public Hat getAccessoryMinimumPrice(){
        return hatRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(HatEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
