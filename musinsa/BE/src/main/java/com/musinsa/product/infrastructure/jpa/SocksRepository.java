package com.musinsa.product.infrastructure.jpa;

import com.musinsa.product.infrastructure.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {
    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<SocksEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<SocksEntity> findFirstByOrderByPriceDescBrandDesc();

    /**
     * 특정 브랜드 양말 조회
     */
    Optional<SocksEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
