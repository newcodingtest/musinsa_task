package com.musinsa.shop.service;

import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OuterService {

    private final OuterRepository outerRepository;

    public Outer getAccessoryMinimumPrice(){
        return outerRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(OuterEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }


}
