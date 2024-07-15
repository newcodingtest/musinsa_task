package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryErrorCode;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Outer;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.HatEntity;
import com.musinsa.product.infrastructure.entity.OuterEntity;
import com.musinsa.product.infrastructure.jpa.OuterRepository;
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
public class OuterServiceTests {

    @Mock
    private OuterRepository outerRepository;

    @InjectMocks
    private OuterService outerService;

    @Mock
    private OuterEntity outerEntity;

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
        Outer outer = outerService.getOuterMinimumPrice();

        //then
        assertNotNull(outer);
        assertEquals("E", outer.getBrand());
        assertEquals(new BigDecimal(5000).stripTrailingZeros(), outer.getPrice().stripTrailingZeros());
    }

    @Test
    public void 아우터_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        OuterEntity outer1 = OuterEntity.builder()
                .brand("D")
                .price(new BigDecimal(5100))
                .build();

        OuterEntity outer2 = OuterEntity.builder()
                .brand("E")
                .price(new BigDecimal(5000))
                .build();

        when(outerRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(outer1));

        //when
        Outer outer = outerService.getOuterMaximumPrice();

        //then
        assertNotNull(outer);
        assertEquals("D", outer.getBrand());
        assertEquals(new BigDecimal(5100).stripTrailingZeros(), outer.getPrice().stripTrailingZeros());
    }


    @Test
    public void 아우터_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(outerRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            outerService.getOuterMinimumPrice();
        });

        assertEquals(CategoryErrorCode
                        .CATEGORY_ITEM_NOT_FOUND
                        .getMessage(),
                exception.getMessage());
    }

    @Test
    public void 새로운_아우터를_생성_할_수_있다() {
        //given
        Outer outer = Outer.builder()
                .brand("아우터")
                .price(new BigDecimal(0))
                .build();


        //when
        outerService.createOuter(outer);

        //then
        verify(outerRepository, times(1)).save(any(OuterEntity.class));
    }

    @Test
    public void 아우터를_수정_할_수_있다(){
        Long id = 1L;
        //given
        Outer outer = Outer.builder()
                .brand("아우터")
                .price(new BigDecimal(0))
                .build();

        when(outerRepository.findById(id)).thenReturn(Optional.of(outerEntity));

        //when
        outerService.updateOuter(id, outer);

        //then
        verify(outerRepository, times(1)).findById(id);
        verify(outerEntity, times(1)).change(outer);
    }

    @Test
    public void id로_아우터를_삭제_할_수_있다(){
        //given
        Long id = 1L;

        //when
        outerService.deleteOuter(id);

        //then
        verify(outerRepository, times(1)).deleteById(id);
    }
}
