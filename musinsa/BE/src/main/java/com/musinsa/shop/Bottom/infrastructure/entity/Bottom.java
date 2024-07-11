package com.musinsa.shop.Bottom.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_bottom_price", columnList = "price"))
@Entity
public class Bottom extends Product {

}
