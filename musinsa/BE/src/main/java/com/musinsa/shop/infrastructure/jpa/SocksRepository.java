package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<SocksEntity, Long> {
    SocksEntity findFirstByOrderByPriceAscBrandDesc();
}
