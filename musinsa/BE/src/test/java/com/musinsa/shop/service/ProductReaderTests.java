package com.musinsa.shop.service;
import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.infrastructure.jpa.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductReaderTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductReader productReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLowerItemsCatByCat() {
        // Given
        List<ProductQueryDto> mockItems = Arrays.asList(
                new ProductQueryDto(ClothingCategory.HAT, "A", BigDecimal.valueOf(100)),
                new ProductQueryDto(ClothingCategory.BAG, "B", BigDecimal.valueOf(200))
        );

        when(productRepository.findCategoryLowestPriceDetails()).thenReturn(mockItems);

        // When
        List<ProductQueryDto> items = productReader.getLowerItemsCatByCat();

        // Then
        assertEquals(2, items.size());
        assertEquals(mockItems.get(0).getCategory(), items.get(0).getCategory());
        assertEquals(mockItems.get(1).getCategory(), items.get(1).getCategory());

        assertEquals(mockItems.get(0).getBrand(), items.get(0).getBrand());
        assertEquals(mockItems.get(1).getBrand(), items.get(1).getBrand());

        assertEquals(mockItems.get(0).getPrice(), items.get(0).getPrice());
        assertEquals(mockItems.get(1).getPrice(), items.get(1).getPrice());
    }

    @Test
    void testGetLowerBrand() {
        // Given
        List<ProductQueryDto.LowerTotalPriceDto> mockBrands = Collections.singletonList(
                ProductQueryDto.LowerTotalPriceDto.builder()
                        .totalPrice(BigDecimal.valueOf(10000))
                        .brand("A")
                        .build()
        );

        when(productRepository.findLowerBrand()).thenReturn(mockBrands);

        // When
        String brand = productReader.getLowerBrand();

        // Then
        assertEquals(mockBrands.get(0).getBrand(), brand);
    }

    @Test
    void testGetLowerItemsByBrand() {
        // Given
        String brand = "A";
        List<ProductQueryDto.LowerBrandDto> mockItems = Arrays.asList(
                ProductQueryDto.LowerBrandDto.builder()
                        .category(ClothingCategory.HAT)
                        .brand(brand)
                        .price(BigDecimal.valueOf(10000))
                        .build(),
                ProductQueryDto.LowerBrandDto.builder()
                        .category(ClothingCategory.BAG)
                        .brand(brand)
                        .price(BigDecimal.valueOf(10000))
                        .build()
                );

        when(productRepository.findLowerItem(brand)).thenReturn(mockItems);

        // When
        List<ProductQueryDto.LowerBrandDto> items = productReader.getLowerItemsByBrand(brand);

        // Then
        assertEquals(mockItems.size(), items.size());
        assertEquals(brand, items.get(0).getBrand());
        assertEquals(mockItems.get(1).getPrice(), items.get(1).getPrice());
    }
}
