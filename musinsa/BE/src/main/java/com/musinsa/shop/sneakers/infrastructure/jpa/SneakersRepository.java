package com.musinsa.shop.sneakers.infrastructure.jpa;

import com.musinsa.shop.sneakers.infrastructure.entity.Sneakers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SneakersRepository extends JpaRepository<Sneakers, Long> {
}
