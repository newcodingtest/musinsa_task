package com.musinsa.shop.infrastructure.jpa;


import com.musinsa.shop.infrastructure.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Long> {
    BagEntity findFirstByOrderByPriceAscBrandDesc();
}
