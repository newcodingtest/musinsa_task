package com.musinsa.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 시작 시 캐시 초기화
 * 
 * */
@Component
public class CacheInitializer implements CommandLineRunner {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void run(String... args) throws Exception {
        cacheManager.getCache("minimumProduct").clear();
        cacheManager.getCache("lowestBrandProduct").clear();
        cacheManager.getCache("productCacheByCategory").clear();
    }
}

