package com.musinsa.shop.service;

import com.musinsa.shop.domain.Socks;
import com.musinsa.shop.domain.Top;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.entity.TopEntity;
import com.musinsa.shop.infrastructure.jpa.TopRepository;
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
public class TopServiceTests {

    @Mock
    private TopRepository topRepository;

    @InjectMocks
    private TopService topService;

    @Test
    public void 상의_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        TopEntity top1 = TopEntity.builder()
                .brand("B")
                .price(new BigDecimal(10500))
                .build();

        TopEntity top2 = TopEntity.builder()
                .brand("C")
                .price(new BigDecimal(10000))
                .build();

        when(topRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(top2));

        //when
        Top top = topService.getTopMinimumPrice();

        //then
        assertNotNull(top);
        assertEquals("C", top.getBrand());
        assertEquals(new BigDecimal(10000).stripTrailingZeros(), top.getPrice().stripTrailingZeros());
    }

    @Test
    public void 상의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        TopEntity top1 = TopEntity.builder()
                .brand("B")
                .price(new BigDecimal(10500))
                .build();

        TopEntity top2 = TopEntity.builder()
                .brand("C")
                .price(new BigDecimal(10000))
                .build();


        when(topRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(top1));

        //when
        Top top = topService.getTopMaximumPrice();

        //then
        assertNotNull(top);
        assertEquals("B", top.getBrand());
        assertEquals(new BigDecimal(10500).stripTrailingZeros(), top.getPrice().stripTrailingZeros());
    }


    @Test
    public void 상의_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
       //given
        when(topRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            topService.getTopMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
