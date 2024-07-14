package com.musinsa.product.service;

import com.musinsa.product.domain.Sneakers;
import com.musinsa.product.infrastructure.entity.SneakersEntity;
import com.musinsa.product.infrastructure.jpa.SneakersRepository;
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
public class SneakersServiceTests {

    @Mock
    private SneakersRepository sneakersRepository;

    @InjectMocks
    private SneakersService sneakersService;

    @Test
    public void 스니커즈_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        SneakersEntity sneakers1 = SneakersEntity.builder()
                .brand("A")
                .price(new BigDecimal(9000))
                .build();

        SneakersEntity sneakers2 = SneakersEntity.builder()
                .brand("B")
                .price(new BigDecimal(9100))
                .build();

        when(sneakersRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(sneakers1));

        //when
        Sneakers sneakers = sneakersService.getSneakersMinimumPrice();

        //then
        assertNotNull(sneakers);
        assertEquals("A", sneakers.getBrand());
        assertEquals(new BigDecimal(9000).stripTrailingZeros(), sneakers.getPrice().stripTrailingZeros());
    }

    @Test
    public void 스니커즈_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        SneakersEntity sneakers1 = SneakersEntity.builder()
                .brand("A")
                .price(new BigDecimal(9000))
                .build();

        SneakersEntity sneakers2 = SneakersEntity.builder()
                .brand("B")
                .price(new BigDecimal(9100))
                .build();

        when(sneakersRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(sneakers2));

        //when
        Sneakers sneakers = sneakersService.getSneakersMaximumPrice();

        //then
        assertNotNull(sneakers);
        assertEquals("B", sneakers.getBrand());
        assertEquals(new BigDecimal(9100).stripTrailingZeros(), sneakers.getPrice().stripTrailingZeros());
    }


    @Test
    public void 스키커즈_정보가_없으면__최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(sneakersRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sneakersService.getSneakersMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
