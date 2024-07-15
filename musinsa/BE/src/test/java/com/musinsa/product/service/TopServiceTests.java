package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryErrorCode;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Top;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.SocksEntity;
import com.musinsa.product.infrastructure.entity.TopEntity;
import com.musinsa.product.infrastructure.jpa.TopRepository;
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
public class TopServiceTests {

    @Mock
    private TopRepository topRepository;

    @InjectMocks
    private TopService topService;

    @Mock
    private TopEntity topEntity;

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
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            topService.getTopMinimumPrice();
        });

        assertEquals(CategoryErrorCode
                        .CATEGORY_ITEM_NOT_FOUND
                        .getMessage(),
                exception.getMessage());
    }

    @Test
    public void 새로운_상의를_생성_할_수_있다() {
        //given
        Top top = Top.builder()
                .brand("상의")
                .price(new BigDecimal(0))
                .build();

        //when
        topService.createTop(top);

        //then
        verify(topRepository, times(1)).save(any(TopEntity.class));
    }

    @Test
    public void 상의를_수정_할_수_있다(){
        Long id = 1L;
        //given
        Top top = Top.builder()
                .brand("상의")
                .price(new BigDecimal(0))
                .build();

        when(topRepository.findById(id)).thenReturn(Optional.of(topEntity));

        //when
        topService.updateTop(id, top);

        //then
        verify(topRepository, times(1)).findById(id);
        verify(topEntity, times(1)).change(top);
    }

    @Test
    public void id로_상의를_삭제_할_수_있다(){
        //given
        Long id = 1L;

        //when
        topService.deleteTop(id);

        //then
        verify(topRepository, times(1)).deleteById(id);
    }
}
