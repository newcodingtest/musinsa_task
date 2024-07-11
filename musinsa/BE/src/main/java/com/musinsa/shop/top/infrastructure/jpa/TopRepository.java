package com.musinsa.shop.top.infrastructure.jpa;

import com.musinsa.shop.top.infrastructure.entity.Top;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopRepository extends JpaRepository<Top, Long> {
}
