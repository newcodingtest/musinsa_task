package com.musinsa.shop.Bottom.infrastructure.jpa;


import com.musinsa.shop.Bottom.infrastructure.entity.Bottom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BottomRepository extends JpaRepository<Bottom, Long> {
}
