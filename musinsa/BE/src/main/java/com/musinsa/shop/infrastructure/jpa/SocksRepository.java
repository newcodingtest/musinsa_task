package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {
    Optional<SocksEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 양말 조회
     */
    Optional<SocksEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
