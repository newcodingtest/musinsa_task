package com.musinsa.shop.outer.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_outer_price", columnList = "price"))
@Entity
public class Outer extends Product {

}
