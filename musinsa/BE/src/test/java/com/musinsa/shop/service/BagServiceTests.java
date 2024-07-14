package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.domain.Bag;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.jpa.BagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BagServiceTests {
    @InjectMocks
    private BagService bagService;

    @Mock
    private BagRepository bagRepository;

    @Test
    public void 가방_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag1 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(2000))
                .build();

        BagEntity bag2 = BagEntity.builder()
                .brand("B")
                .price(new BigDecimal(2100))
                .build();

        when(bagRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(bag1));

        //when
        Bag bag = bagService.getBagMinimumPrice();

        //then
        assertNotNull(bag);
        assertEquals("A", bag.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }

    @Test
    public void 액세서리의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag1 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(2000))
                .build();

        BagEntity bag2 = BagEntity.builder()
                .brand("B")
                .price(new BigDecimal(2100))
                .build();

        when(bagRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(bag2));

        //when
        Bag bag = bagService.getBagMaximumPrice();

        //then
        assertNotNull(bag);
        assertEquals("B", bag.getBrand());
        assertEquals(new BigDecimal(2100).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }


    @Test
    public void 가방_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        // Given
        when(bagRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bagService.getBagMinimumPrice();
        });

        assertEquals("No accessories found", exception.getMessage());
    }
}
