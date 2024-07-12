package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.HatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatRepository extends JpaRepository<HatEntity, Long> {
    HatEntity findFirstByOrderByPriceAscBrandDesc();
}
