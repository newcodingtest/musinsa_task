package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HatRepository extends JpaRepository<HatEntity, Long> {
    Optional<HatEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 모자 조회
     */
    Optional<HatEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
