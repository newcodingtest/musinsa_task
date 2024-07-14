package com.musinsa.product.service;

import com.musinsa.product.domain.Hat;
import com.musinsa.product.infrastructure.entity.HatEntity;
import com.musinsa.product.infrastructure.jpa.HatRepository;
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
public class HatServiceTests {

    @Mock
    private HatRepository hatRepository;

    @InjectMocks
    private HatService hatService;

    @Test
    public void 모자_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        HatEntity hat1 = HatEntity.builder()
                .brand("D")
                .price(new BigDecimal(1500))
                .build();

        HatEntity hat2 = HatEntity.builder()
                .brand("G")
                .price(new BigDecimal(1700))
                .build();

        when(hatRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(hat1));

        //when
        Hat hat = hatService.getHatMinimumPrice();

        //then
        assertNotNull(hat);
        assertEquals("D", hat.getBrand());
        assertEquals(new BigDecimal(1500).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }

    @Test
    public void 모자의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        HatEntity hat1 = HatEntity.builder()
                .brand("D")
                .price(new BigDecimal(1500))
                .build();

        HatEntity hat2 = HatEntity.builder()
                .brand("G")
                .price(new BigDecimal(1700))
                .build();

        when(hatRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(hat2));

        //when
        Hat hat = hatService.getHatMaximumPrice();

        //then
        assertNotNull(hat);
        assertEquals("G", hat.getBrand());
        assertEquals(new BigDecimal(1700).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }

    @Test
    public void 모자_정보가_없으면_최저가격_브랜드와_가격을_조회할_수_없다() {
        // given
        // when
        when(hatRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hatService.getHatMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }




    @Test
    public void 특정_브랜드의_최저가격_모자를_조회할_수_있다(){
        //given
        HatEntity hat1 = HatEntity.builder()
                .brand("D")
                .price(new BigDecimal(1500))
                .build();

        when(hatRepository.findFirstByBrandOrderByPriceAsc("D"))
                .thenReturn(Optional.of(hat1));

        //when
        Hat hat = hatService.getHatMinimumPriceByBrand("D");
        
        //then
        assertEquals(hat1.toModel().getBrand(), hat.getBrand());
        assertEquals(hat1.toModel().getPrice(), hat.getPrice());
    }
}
