package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SneakersRepository extends JpaRepository<SneakersEntity, Long> {

    /**
     * 가장 싼 악세서리 조회*
     *
     * */
    Optional<SneakersEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 가장 비싼 악세서리 조회*
     *
     * */
    Optional<SneakersEntity> findFirstByOrderByPriceDescBrandDesc();


    /**
     * 특정 브랜드 스니커즈 조회
     */
    Optional<SneakersEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
