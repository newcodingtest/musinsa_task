package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.BottomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottomRepository extends JpaRepository<BottomEntity, Long> {
    BottomEntity findFirstByOrderByPriceAscBrandDesc();
}
