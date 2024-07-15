package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryErrorCode;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Bottom;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.BagEntity;
import com.musinsa.product.infrastructure.entity.BottomEntity;
import com.musinsa.product.infrastructure.jpa.BottomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BottomServiceTests {

    @Mock
    private BottomRepository bottomRepository;

    @InjectMocks
    private BottomService bottomService;

    @Mock
    private BottomEntity bottomEntity;

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
        Bottom bottom = bottomService.getBottomMinimumPrice();

        //then
        assertNotNull(bottom);
        assertEquals("H", bottom.getBrand());
        assertEquals(new BigDecimal(3100).stripTrailingZeros(), bottom.getPrice().stripTrailingZeros());
    }

    @Test
    public void 바지의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BottomEntity bottom1 = BottomEntity.builder()
                .brand("H")
                .price(new BigDecimal(3100))
                .build();

        BottomEntity bottom2 = BottomEntity.builder()
                .brand("A")
                .price(new BigDecimal(4200))
                .build();

        when(bottomRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(bottom2));

        //when
        Bottom bottom = bottomService.getBottomMaximumPrice();

        //then
        assertNotNull(bottom);
        assertEquals("A", bottom.getBrand());
        assertEquals(new BigDecimal(4200).stripTrailingZeros(), bottom.getPrice().stripTrailingZeros());
    }


    @Test
    public void 바지_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(bottomRepository.findFirstByOrderByPriceAscBrandDesc())
                            .thenReturn(Optional.empty()
        );

        //when
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bottomService.getBottomMinimumPrice();
        });

        assertEquals(CategoryErrorCode
                        .CATEGORY_ITEM_NOT_FOUND
                        .getMessage(),
                exception.getMessage());
    }

    @Test
    public void 새로운_바지를_생성_할_수_있다() {
        //given
        Bottom bottom = Bottom.builder()
                .brand("바지")
                .price(new BigDecimal(0))
                .build();

        //when
        bottomService.createBottom(bottom);

        //then
        verify(bottomRepository, times(1)).save(any(BottomEntity.class));
    }

    @Test
    public void 바지를_수정_할_수_있다(){
        Long id = 1L;
        //given
        Bottom bottom = Bottom.builder()
                .brand("바지")
                .price(new BigDecimal(0))
                .build();

        when(bottomRepository.findById(id)).thenReturn(Optional.of(bottomEntity));

        //when
        bottomService.updateBottom(id, bottom);

        //then
        verify(bottomRepository, times(1)).findById(id);
        verify(bottomEntity, times(1)).change(bottom);
    }

    @Test
    public void id로_바지를_삭제_할_수_있다(){
        //given
        Long id = 1L;

        //when
        bottomService.deleteBottom(id);

        //then
        verify(bottomRepository, times(1)).deleteById(id);
    }
}
