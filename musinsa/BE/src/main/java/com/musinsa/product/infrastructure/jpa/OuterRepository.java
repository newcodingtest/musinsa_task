package com.musinsa.product.infrastructure.jpa;

import com.musinsa.product.infrastructure.entity.OuterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OuterRepository extends JpaRepository<OuterEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<OuterEntity> findFirstByOrderByPriceAscBrandDesc();


    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<OuterEntity> findFirstByOrderByPriceDescBrandDesc();


    /**
     * 특정 브랜드 아우터 조회
     */
    Optional<OuterEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
