package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.infrastructure.entity.ProductEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
                .category(ClothingCategory.OUTER)
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
                .category(ClothingCategory.TOP)
                .brand("A")
                .price(BigDecimal.valueOf(4000))
                .build();
        entityManager.persist(product3);

        ProductEntity product4 = ProductEntity.builder()
                .category(ClothingCategory.TOP)
                .brand("B")
                .price(BigDecimal.valueOf(5000))
                .build();
        entityManager.persist(product4);

        ProductEntity product5 = ProductEntity.builder()
                .category(ClothingCategory.BOTTOM)
                .brand("A")
                .price(BigDecimal.valueOf(4000))
                .build();
        entityManager.persist(product5);

        ProductEntity product6 = ProductEntity.builder()
                .category(ClothingCategory.BOTTOM)
                .brand("B")
                .price(BigDecimal.valueOf(7000))
                .build();
        entityManager.persist(product6);

        ProductEntity product7 = ProductEntity.builder()
                .category(ClothingCategory.SNEAKERS)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product7);

        ProductEntity product8 = ProductEntity.builder()
                .category(ClothingCategory.SNEAKERS)
                .brand("B")
                .price(BigDecimal.valueOf(9000))
                .build();
        entityManager.persist(product8);

        ProductEntity product9 = ProductEntity.builder()
                .category(ClothingCategory.BAG)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product9);

        ProductEntity product10 = ProductEntity.builder()
                .category(ClothingCategory.BAG)
                .brand("B")
                .price(BigDecimal.valueOf(9000))
                .build();
        entityManager.persist(product10);

        ProductEntity product11 = ProductEntity.builder()
                .category(ClothingCategory.HAT)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product11);

        ProductEntity product12 = ProductEntity.builder()
                .category(ClothingCategory.HAT)
                .brand("B")
                .price(BigDecimal.valueOf(9000))
                .build();
        entityManager.persist(product12);

        ProductEntity product13 = ProductEntity.builder()
                .category(ClothingCategory.SOCKS)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product13);

        ProductEntity product14 = ProductEntity.builder()
                .category(ClothingCategory.SOCKS)
                .brand("B")
                .price(BigDecimal.valueOf(9000))
                .build();
        entityManager.persist(product14);

        ProductEntity product15 = ProductEntity.builder()
                .category(ClothingCategory.ACCESSORY)
                .brand("A")
                .price(BigDecimal.valueOf(10000))
                .build();
        entityManager.persist(product15);

        ProductEntity product16 = ProductEntity.builder()
                .category(ClothingCategory.ACCESSORY)
                .brand("B")
                .price(BigDecimal.valueOf(9000))
                .build();
        entityManager.persist(product16);


        entityManager.flush();
        entityManager.clear();
    }

    @DisplayName("카테고리 별로 가장 싼 제품을 찾을 수 있다.")
    @Test
    public void testFindCategoryLowestPriceDetails() {
        // When
        List<ProductQueryDto> results = productRepository.findCategoryLowestPriceDetails();

        // Then
        assertThat(results).hasSize(8); // Expecting one result per category
        assertThat(results).extracting(ProductQueryDto::getCategory)
                .containsExactlyInAnyOrder(
                        ClothingCategory.OUTER,
                        ClothingCategory.TOP,
                        ClothingCategory.BOTTOM,
                        ClothingCategory.SNEAKERS,
                        ClothingCategory.BAG,
                        ClothingCategory.HAT,
                        ClothingCategory.SOCKS,
                        ClothingCategory.ACCESSORY
                );

        assertThat(results).extracting(ProductQueryDto::getPrice)
                .containsExactlyInAnyOrder(BigDecimal.valueOf(8000.00).setScale(2),
                        BigDecimal.valueOf(4000.00).setScale(2),
                        BigDecimal.valueOf(4000.00).setScale(2),
                        BigDecimal.valueOf(9000.00).setScale(2),
                        BigDecimal.valueOf(9000.00).setScale(2),
                        BigDecimal.valueOf(9000.00).setScale(2),
                        BigDecimal.valueOf(9000.00).setScale(2),
                        BigDecimal.valueOf(9000.00).setScale(2));
        assertThat(results).extracting(ProductQueryDto::getBrand)
                .containsExactlyInAnyOrder("B", "A", "B", "A", "B", "B", "B", "B");
    }




    @DisplayName("브랜드 별로 카테고리 총액이 가장 싼 브랜드를 찾을 수 있다.")
    @Test
    public void testFindLowerBrandForTotalPrice(){
        //given
        //when
        List<ProductQueryDto.LowerTotalPriceDto> results =  productRepository
                .findLowerBrand();


        //then
        assertThat(results).extracting(ProductQueryDto.LowerTotalPriceDto::getBrand)
                        .contains("B");
        assertThat(results).extracting(ProductQueryDto.LowerTotalPriceDto::getTotalPrice)
                .contains(BigDecimal.valueOf(65000).setScale(2));
    }

    @DisplayName("가장 싼 브랜드로 카테고리 별 상품을 찾을 수 있다.")
    @Test
    public void testFindLowerCategoryByBrand() {
        //given
        final String brand = "A";

        //when
        List<ProductQueryDto.LowerBrandDto> results = productRepository.findLowerItem(brand);

        //then
        assertThat(results).hasSize(8);
        assertThat(results)
                .extracting(ProductQueryDto.LowerBrandDto::getBrand)
                .contains(brand);
        assertThat(results)
                .extracting(ProductQueryDto.LowerBrandDto::getCategory)
                .containsExactlyInAnyOrder(
                        ClothingCategory.OUTER,
                        ClothingCategory.TOP,
                        ClothingCategory.BOTTOM,
                        ClothingCategory.SNEAKERS,
                        ClothingCategory.BAG,
                        ClothingCategory.HAT,
                        ClothingCategory.SOCKS,
                        ClothingCategory.ACCESSORY);
        assertThat(results)
                .extracting(ProductQueryDto.LowerBrandDto::getPrice)
                .containsExactlyInAnyOrder(
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(4000).setScale(2),
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(10000).setScale(2),
                        BigDecimal.valueOf(4000).setScale(2)
                );
    }


}
