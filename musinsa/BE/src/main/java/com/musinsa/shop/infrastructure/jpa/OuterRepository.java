package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OuterRepository extends JpaRepository<OuterEntity, Long> {
    Optional<OuterEntity> findFirstByOrderByPriceAscBrandDesc();

    /**
     * 특정 브랜드 아우터 조회
     */
    Optional<OuterEntity> findFirstByBrandOrderByPriceAsc(String brand);
}
