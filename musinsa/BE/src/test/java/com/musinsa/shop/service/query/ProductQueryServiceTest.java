package com.musinsa.shop.service.query;

import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.service.ProductReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductQueryServiceTest {
    @Mock
    private ProductReader productReader;

    @InjectMocks
    private ProductQueryService productQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLowerItems() {
        // Given
        List<ProductQueryDto> mockItems = Arrays.asList(
                ProductQueryDto.builder()
                        .brand("A")
                        .category(ClothingCategory.HAT)
                        .price(BigDecimal.valueOf(10000))
                        .build(),
                ProductQueryDto.builder()
                        .brand("B")
                        .category(ClothingCategory.BAG)
                        .price(BigDecimal.valueOf(10000))
                        .build()
        );

        when(productReader.getLowerItemsCatByCat()).thenReturn(mockItems);

        // When
        List<ProductQueryDto> items = productQueryService.getLowerItems();

        // Then
        assertEquals(2, items.size());

        assertEquals(mockItems.get(0).getBrand(), items.get(0).getBrand());
        assertEquals(mockItems.get(1).getBrand(), items.get(1).getBrand());

        assertEquals(mockItems.get(0).getCategory(), items.get(0).getCategory());
        assertEquals(mockItems.get(1).getCategory(), items.get(1).getCategory());

        assertEquals(mockItems.get(0).getPrice(), items.get(0).getPrice());
        assertEquals(mockItems.get(1).getPrice(), items.get(1).getPrice());


    }

    @Test
    void testGetLowerBrandItems() {
        // Given
        String lowerBrand = "A";
        List<ProductQueryDto.LowerBrandDto> mockLowerItems = Arrays.asList(
                ProductQueryDto.LowerBrandDto.builder()
                        .brand(lowerBrand)
                        .price(BigDecimal.valueOf(10000))
                        .category(ClothingCategory.HAT)
                        .build(),
                ProductQueryDto.LowerBrandDto.builder()
                        .brand(lowerBrand)
                        .price(BigDecimal.valueOf(90000))
                        .category(ClothingCategory.BAG)
                        .build());

        when(productReader.getLowerBrand()).thenReturn(lowerBrand);
        when(productReader.getLowerItemsByBrand(lowerBrand)).thenReturn(mockLowerItems);

        // When
        List<ProductQueryDto> lowerBrandItems = productQueryService.getLowerBrandItems();

        // Then
        assertEquals(mockLowerItems.size(), lowerBrandItems.size());
        assertEquals(lowerBrand, lowerBrandItems.get(0).getBrand());
        assertEquals(lowerBrand, lowerBrandItems.get(1).getBrand());
        assertEquals(mockLowerItems.get(0).getPrice(), lowerBrandItems.get(0).getPrice());
        assertEquals(mockLowerItems.get(1).getPrice(), lowerBrandItems.get(1).getPrice());
        assertEquals(mockLowerItems.get(0).getCategory(), lowerBrandItems.get(0).getCategory());
        assertEquals(mockLowerItems.get(1).getCategory(), lowerBrandItems.get(1).getCategory());
    }
}
