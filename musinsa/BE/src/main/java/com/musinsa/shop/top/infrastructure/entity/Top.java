package com.musinsa.shop.top.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_top_price", columnList = "price"))
@Entity
public class Top extends Product {
}
