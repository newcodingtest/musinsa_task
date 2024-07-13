package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.TopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopRepository extends JpaRepository<TopEntity, Long> {
    Optional<TopEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 상의 조회
     */
    Optional<TopEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
