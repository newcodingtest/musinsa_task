package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.QProductEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final JPAQueryFactory jpaQueryFactory;


    public List<ProductQueryDto> findCategoryLowestPriceDetails() {
        QProductEntity product = QProductEntity.productEntity;

        var subQuery = jpaQueryFactory
                .select(product.category, product.price.min())
                .from(product)
                .groupBy(product.category);

        // 튜플을 사용하여 category와 price를 비교하는 메인 쿼리
        SimplePath<String> categoryPath = Expressions.path(String.class, "category");
        NumberPath<BigDecimal> pricePath = Expressions.numberPath(BigDecimal.class, "price");

        return jpaQueryFactory
                .select(Projections.constructor(ProductQueryDto.class,
                        product.category,
                        product.brand,
                        product.price
                ))
                .from(product)
                .where(product.price.in(
                        JPAExpressions
                                .select(product.price.min())
                                .from(product)
                                .where(product.category.eq(product.category))
                                .groupBy(product.category)
                ))
                .fetch();
    }
    /**
     1. 총액이 가장 싼놈 구하기

     SELECT p.brand, SUM(p.price) AS totalAmount
     FROM Product p
     GROUP BY p.brand
     HAVING COUNT(DISTINCT p.category) = (SELECT COUNT(DISTINCT p2.category) FROM Product p2)
     ORDER BY totalAmount ASC
     * */
    public List<ProductQueryDto.LowerTotalPriceDto> findLowerBrand(){
        QProductEntity product = QProductEntity.productEntity;

        var subQuery = jpaQueryFactory
                .select(product.category.countDistinct())
                .from(product);

        return jpaQueryFactory
                .select(Projections.constructor(ProductQueryDto.LowerTotalPriceDto.class,
                        product.brand,
                        product.price.sum().as("price"))
                )
                .from(product)
                .groupBy(product.brand)
                .having(product.category.countDistinct().eq(subQuery))
                .orderBy(product.price.sum().asc())
                .limit(1)
                .fetch();
    }

    /**
     2.가장 저렴한 브랜드의 카테고리와 가격 조회

     select category, brand, MIN(price)
     from product
     where category in (select category from product group by category)
     and brand='A'
     group by category

     [리펙토링]
     SELECT category, brand, MIN(price)
     FROM product
     WHERE brand = 'A'
     GROUP BY category, brand;

     *
     * */
    public List<ProductQueryDto.LowerBrandDto> findLowerItem(final String brand){
        QProductEntity product = QProductEntity.productEntity;

         return jpaQueryFactory
                .select(Projections.constructor(ProductQueryDto.LowerBrandDto.class,
                        product.category,
                        product.brand,
                        product.price.min()))
                .from(product)
                .where(product.brand.eq(brand))
                .groupBy(product.category, product.brand)
                 .fetch();
    }


}
