package com.musinsa.common.service;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    @Caching(evict = {
            @CacheEvict(value = "minimumProduct", allEntries = true),
            @CacheEvict(value = "lowestBrandProduct", allEntries = true),
            @CacheEvict(value = "productCacheByCategory", allEntries = true)
    })
    public void evictAllSpecifiedCaches() {
    }

}
