package com.musinsa.shop.service;

import com.musinsa.shop.domain.Bag;
import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BottomService {

    private final BottomRepository bottomRepository;

    public Bottom getAccessoryMinimumPrice(){
        return bottomRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(BottomEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
