package com.musinsa.shop.service;

import com.musinsa.shop.infrastructure.jpa.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SocksService {

    private final SocksRepository socksRepository;

}
