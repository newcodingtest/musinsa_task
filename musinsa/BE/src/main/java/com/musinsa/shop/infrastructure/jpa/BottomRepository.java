package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BottomRepository extends JpaRepository<BottomEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<BottomEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<BottomEntity> findFirstByOrderByPriceDescBrandDesc();

    /**
     * 특정 브랜드 바지 조회
     */
    Optional<BottomEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
