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
}
