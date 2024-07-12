package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakersRepository extends JpaRepository<SneakersEntity, Long> {
    SneakersEntity findFirstByOrderByPriceAscBrandDesc();
}
