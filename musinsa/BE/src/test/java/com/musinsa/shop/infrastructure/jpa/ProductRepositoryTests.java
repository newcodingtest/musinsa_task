package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.infrastructure.entity.ProductEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(ProductRepository.class)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    public void setup() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);

        // Insert test data
        ProductEntity product1 = ProductEntity.builder()
                .category(ClothingCategory.HAT)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product1);

        ProductEntity product2 = ProductEntity.builder()
                .category(ClothingCategory.OUTER)
                .brand("B")
                .price(BigDecimal.valueOf(8000))
                .build();
        entityManager.persist(product2);

        ProductEntity product3 = ProductEntity.builder()
                .category(ClothingCategory.OUTER)
                .brand("C")
                .price(BigDecimal.valueOf(6000))
                .build();
        entityManager.persist(product3);

        ProductEntity product4 = ProductEntity.builder()
                .category(ClothingCategory.TOP)
                .brand("D")
                .price(BigDecimal.valueOf(4000))
                .build();
        entityManager.persist(product4);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void testFindCategoryLowestPriceDetails() {
        // When
        List<ProductQueryDto> results = productRepository.findCategoryLowestPriceDetails();

        // Then
        assertThat(results).hasSize(3); // Expecting one result per category
        assertThat(results).extracting(ProductQueryDto::getCategory)
                .containsExactlyInAnyOrder(ClothingCategory.HAT,
                        ClothingCategory.OUTER,
                        ClothingCategory.TOP);
        assertThat(results).extracting(ProductQueryDto::getPrice)
                .containsExactlyInAnyOrder(BigDecimal.valueOf(10000.00).setScale(2),
                        BigDecimal.valueOf(6000.00).setScale(2),
                        BigDecimal.valueOf(4000.00).setScale(2));
        assertThat(results).extracting(ProductQueryDto::getBrand)
                .containsExactlyInAnyOrder("A", "C", "D");
    }


}
