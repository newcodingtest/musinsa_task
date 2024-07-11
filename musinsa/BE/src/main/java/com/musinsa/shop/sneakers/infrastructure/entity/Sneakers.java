package com.musinsa.shop.sneakers.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_sneakers_price", columnList = "price"))
@Entity
public class Sneakers extends Product {

}
