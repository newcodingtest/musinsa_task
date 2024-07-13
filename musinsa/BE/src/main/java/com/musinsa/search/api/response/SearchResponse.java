package com.musinsa.search.api.response;

import com.musinsa.search.domain.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;


@ToString
@Getter
@Builder
public class SearchResponse {
    List<Product> products;
    BigDecimal totalPrice;
}
