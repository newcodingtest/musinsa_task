package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<AccessoryEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<AccessoryEntity> findFirstByOrderByPriceDescBrandDesc();

    /**
     * 가장 싼 특정 브랜드 액세서리 조회
     */
    Optional<AccessoryEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
