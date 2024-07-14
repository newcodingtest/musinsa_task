package com.musinsa.product.infrastructure.jpa;


import com.musinsa.product.infrastructure.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<BagEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<BagEntity> findFirstByOrderByPriceDescBrandDesc();

    /**
     * 특정 브랜드 가방 조회
     */
    Optional<BagEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
