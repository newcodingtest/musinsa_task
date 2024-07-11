package com.musinsa.shop.Hat.infrastructure.jpa;


import com.musinsa.shop.Hat.infrastructure.entity.Hat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HatRepository extends JpaRepository<Hat, Long> {
}
