package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.TopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<TopEntity, Long> {
    TopEntity findFirstByOrderByPriceAscBrandDesc();
}
