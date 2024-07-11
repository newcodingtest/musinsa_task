package com.musinsa.shop.bag.infrastructure.entity;

import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Table(indexes = @Index(name = "idx_bag_price", columnList = "price"))
@Entity
public class Bag extends Product {


}
