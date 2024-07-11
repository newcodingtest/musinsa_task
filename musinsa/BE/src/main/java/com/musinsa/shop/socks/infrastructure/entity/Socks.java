package com.musinsa.shop.socks.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_socks_price", columnList = "price"))
@Entity
public class Socks extends Product {

}