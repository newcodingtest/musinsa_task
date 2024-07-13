package com.musinsa.shop.service;

import com.musinsa.shop.domain.Outer;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.jpa.OuterRepository;
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
public class OuterServiceTests {

    @Mock
    private OuterRepository outerRepository;

    @InjectMocks
    private OuterService outerService;

    @Test
    public void 아우터_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        OuterEntity outer1 = OuterEntity.builder()
                .brand("D")
                .price(new BigDecimal(5100))
                .build();

        OuterEntity outer2 = OuterEntity.builder()
                .brand("E")
                .price(new BigDecimal(5000))
                .build();

        when(outerRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(outer2));

        //when
        Outer outer = outerService.getAccessoryMinimumPrice();

        //then
        assertNotNull(outer);
        assertEquals("E", outer.getBrand());
        assertEquals(new BigDecimal(5000).stripTrailingZeros(), outer.getPrice().stripTrailingZeros());
    }


    @Test
    public void 아우터_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(outerRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            outerService.getAccessoryMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
