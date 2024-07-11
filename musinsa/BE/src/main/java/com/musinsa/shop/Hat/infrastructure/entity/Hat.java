package com.musinsa.shop.Hat.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_hat_price", columnList = "price"))
@Entity
public class Hat extends Product {


}
