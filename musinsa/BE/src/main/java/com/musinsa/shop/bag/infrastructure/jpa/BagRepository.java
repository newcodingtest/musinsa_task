package com.musinsa.shop.bag.infrastructure.jpa;


import com.musinsa.shop.accessory.infrastructure.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Accessory, Long> {
}
