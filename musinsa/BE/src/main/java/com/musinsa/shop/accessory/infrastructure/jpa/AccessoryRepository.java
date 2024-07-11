package com.musinsa.shop.accessory.infrastructure.jpa;


import com.musinsa.shop.accessory.infrastructure.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    // 고객은 카테고리 별로 최저가격인 브랜드와 가격을 조회하고 총액이 얼마인지 확인할 수 있어야 합니다
    Accessory findFirstByOrderByPriceAsc();
}
