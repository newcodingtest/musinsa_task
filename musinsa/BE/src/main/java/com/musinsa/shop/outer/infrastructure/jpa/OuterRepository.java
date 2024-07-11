package com.musinsa.shop.outer.infrastructure.jpa;

import com.musinsa.shop.outer.infrastructure.entity.Outer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuterRepository extends JpaRepository<Outer, Long> {
}
