package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SneakersRepository extends JpaRepository<SneakersEntity, Long> {
    Optional<SneakersEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 스니커즈 조회
     */
    Optional<SneakersEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
