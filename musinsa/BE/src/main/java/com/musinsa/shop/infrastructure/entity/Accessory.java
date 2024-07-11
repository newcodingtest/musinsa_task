package com.musinsa.shop.infrastructure.entity;
import jakarta.persistence.*;

@Entity
public class Accessory extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
