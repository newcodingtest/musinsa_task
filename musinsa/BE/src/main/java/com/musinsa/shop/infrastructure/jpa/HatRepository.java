package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HatRepository extends JpaRepository<HatEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<HatEntity> findFirstByOrderByPriceAscBrandDesc();


    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<HatEntity> findFirstByOrderByPriceDescBrandDesc();

    /**
     * 특정 브랜드 모자 조회
     */
    Optional<HatEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
