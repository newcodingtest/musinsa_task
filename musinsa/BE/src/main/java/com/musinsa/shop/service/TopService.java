package com.musinsa.shop.service;

import com.musinsa.shop.domain.Socks;
import com.musinsa.shop.domain.Top;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.entity.TopEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TopService {

    private final TopRepository topRepository;

    public Top getAccessoryMinimumPrice(){
        return topRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(TopEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }

}
