package com.musinsa.shop.infrastructure.jpa;


import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Long> {
    Optional<BagEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 가방 조회
     */
    Optional<BagEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
