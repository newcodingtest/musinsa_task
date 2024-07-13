package com.musinsa.shop.service;

import com.musinsa.shop.domain.Sneakers;
import com.musinsa.shop.domain.Socks;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocksService {

    private final SocksRepository socksRepository;

    public Socks getAccessoryMinimumPrice(){
        return socksRepository.findFirstByOrderByPriceAscBrandDesc()
                .map(SocksEntity::toModel)
                .orElseThrow(() -> new RuntimeException("No accessories found"));
    }
}
