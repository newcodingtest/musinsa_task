package com.musinsa.shop.service;


import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.infrastructure.jpa.ProductRepository;
import com.musinsa.shop.service.query.ProductQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

//@SpringBootTest
public class ProductQueryServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductQueryService productQueryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetLowerItems() {
        //given
        List<ProductQueryDto> mockItems = Arrays.asList(
                new ProductQueryDto(ClothingCategory.BAG, "A", BigDecimal.valueOf(10000.00)),
                new ProductQueryDto(ClothingCategory.HAT, "B",BigDecimal.valueOf(6000.00)),
                new ProductQueryDto(ClothingCategory.ACCESSORY, "C",BigDecimal.valueOf(4000.00))
        );


        when(productRepository.findCategoryLowestPriceDetails())
                .thenReturn(mockItems);

        //when
        List<ProductQueryDto> items = productQueryService.getLowerItems();

        //then
        assertThat(items).isNotNull();
        assertThat(items).hasSize(3);
        assertThat(items).extracting(ProductQueryDto::getPrice)
                .containsExactlyInAnyOrder(BigDecimal.valueOf(10000.00),
                        BigDecimal.valueOf(6000.00),
                        BigDecimal.valueOf(4000.00));
    }
}
