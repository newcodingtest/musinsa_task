package com.musinsa.product.service;

import com.musinsa.product.domain.Socks;
import com.musinsa.product.infrastructure.entity.SocksEntity;
import com.musinsa.product.infrastructure.jpa.SocksRepository;
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
public class SocksServiceTests {

    @Mock
    private SocksRepository socksRepository;

    @InjectMocks
    private SocksService socksService;

    @Test
    public void 양말_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        SocksEntity socks1 = SocksEntity.builder()
                .brand("A")
                .price(new BigDecimal(1800))
                .build();

        SocksEntity socks2 = SocksEntity.builder()
                .brand("I")
                .price(new BigDecimal(1700))
                .build();

        when(socksRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(socks2));

        //when
        Socks socks = socksService.getSocksMinimumPrice();

        //then
        assertNotNull(socks);
        assertEquals("I", socks.getBrand());
        assertEquals(new BigDecimal(1700).stripTrailingZeros(), socks.getPrice().stripTrailingZeros());
    }

    @Test
    public void 양말_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        SocksEntity socks1 = SocksEntity.builder()
                .brand("A")
                .price(new BigDecimal(1800))
                .build();

        SocksEntity socks2 = SocksEntity.builder()
                .brand("I")
                .price(new BigDecimal(1700))
                .build();


        when(socksRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(socks1));

        //when
        Socks socks = socksService.getSocksMaximumPrice();

        //then
        assertNotNull(socks);
        assertEquals("A", socks.getBrand());
        assertEquals(new BigDecimal(1800).stripTrailingZeros(), socks.getPrice().stripTrailingZeros());
    }


    @Test
    public void 양말_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(socksRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            socksService.getSocksMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
