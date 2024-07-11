package com.musinsa.shop.accessory.infrastructure.entity;
import com.musinsa.shop.common.entity.Product;
import jakarta.persistence.*;


@Table(indexes = @Index(name = "idx_accessory_price", columnList = "price"))
@Entity
public class Accessory extends Product {
}
