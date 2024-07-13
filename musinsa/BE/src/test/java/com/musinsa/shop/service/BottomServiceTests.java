package com.musinsa.shop.service;

import com.musinsa.shop.domain.Bottom;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.jpa.BottomRepository;
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
public class BottomServiceTests {

    @Mock
    private BottomRepository bottomRepository;

    @InjectMocks
    private BottomService bottomService;

    @Test
    public void 바지_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BottomEntity bottom1 = BottomEntity.builder()
                .brand("H")
                .price(new BigDecimal(3100))
                .build();

        BottomEntity bottom2 = BottomEntity.builder()
                .brand("A")
                .price(new BigDecimal(4200))
                .build();

        when(bottomRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(bottom1));

        //when
        Bottom bottom = bottomService.getAccessoryMinimumPrice();

        //then
        assertNotNull(bottom);
        assertEquals("H", bottom.getBrand());
        assertEquals(new BigDecimal(3100).stripTrailingZeros(), bottom.getPrice().stripTrailingZeros());
    }


    @Test
    public void 바지_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        // given
        when(bottomRepository.findFirstByOrderByPriceAscBrandDesc())
                            .thenReturn(Optional.empty()
        );

        // when
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bottomService.getAccessoryMinimumPrice();
        });

        //then
        assertEquals("No accessories found", exception.getMessage());
    }
}
