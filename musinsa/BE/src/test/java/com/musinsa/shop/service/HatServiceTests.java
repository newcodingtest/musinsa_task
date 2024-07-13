package com.musinsa.shop.service;

import com.musinsa.shop.domain.Hat;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.jpa.HatRepository;
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
        Hat hat = hatService.getAccessoryMinimumPrice();

        //then
        assertNotNull(hat);
        assertEquals("D", hat.getBrand());
        assertEquals(new BigDecimal(1500).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }


    @Test
    public void 모자_정보가_없으면_최저가격_브랜드와_가격을_조회할_수_없다() {
        // given
        when(hatRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            hatService.getAccessoryMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
