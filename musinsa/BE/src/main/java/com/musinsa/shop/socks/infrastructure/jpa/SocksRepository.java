package com.musinsa.shop.socks.infrastructure.jpa;

import com.musinsa.shop.socks.infrastructure.entity.Socks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
}
