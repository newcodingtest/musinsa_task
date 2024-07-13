package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BottomRepository extends JpaRepository<BottomEntity, Long> {
    Optional<BottomEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 바지 조회
     */
    Optional<BottomEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
