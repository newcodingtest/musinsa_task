package com.musinsa.product.infrastructure.jpa;

import com.musinsa.product.infrastructure.entity.TopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopRepository extends JpaRepository<TopEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<TopEntity> findFirstByOrderByPriceAscBrandDesc();


    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<TopEntity> findFirstByOrderByPriceDescBrandDesc();


    /**
     * 특정 브랜드 상의 조회
     */
    Optional<TopEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
