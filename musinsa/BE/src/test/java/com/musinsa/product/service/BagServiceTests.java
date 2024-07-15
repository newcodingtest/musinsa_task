package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryErrorCode;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.domain.Bag;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.entity.BagEntity;
import com.musinsa.product.infrastructure.jpa.BagRepository;
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
public class BagServiceTests {
    @InjectMocks
    private BagService bagService;

    @Mock
    private BagRepository bagRepository;

    @Mock
    private BagEntity bagEntity;

    @Test
    public void 가방_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag1 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(2000))
                .build();

        BagEntity bag2 = BagEntity.builder()
                .brand("B")
                .price(new BigDecimal(2100))
                .build();

        when(bagRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(bag1));

        //when
        Bag bag = bagService.getBagMinimumPrice();

        //then
        assertNotNull(bag);
        assertEquals("A", bag.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }

    @Test
    public void 액세서리의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag1 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(2000))
                .build();

        BagEntity bag2 = BagEntity.builder()
                .brand("B")
                .price(new BigDecimal(2100))
                .build();

        when(bagRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(bag2));

        //when
        Bag bag = bagService.getBagMaximumPrice();

        //then
        assertNotNull(bag);
        assertEquals("B", bag.getBrand());
        assertEquals(new BigDecimal(2100).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }


    @Test
    public void 가방_정보가_없으면_최저가격의_브랜드와_가격을_조회할_수_없다() {
        //given
        when(bagRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bagService.getBagMinimumPrice();
        });

        assertEquals(CategoryErrorCode
                        .CATEGORY_ITEM_NOT_FOUND
                        .getMessage(),
                exception.getMessage());
    }

    @Test
    public void 새로운_가방을_생성_할_수_있다() {
        //given
        Bag bag = Bag.builder()
                .brand("가방")
                .price(new BigDecimal(0))
                .build();

        //when
        bagService.createBag(bag);

        //then
        verify(bagRepository, times(1)).save(any(BagEntity.class));
    }

    @Test
    public void 가방을_수정_할_수_있다(){
        Long id = 1L;
        //given
        Bag bag = Bag.builder()
                .brand("가방")
                .price(new BigDecimal(0))
                .build();

        when(bagRepository.findById(id)).thenReturn(Optional.of(bagEntity));

        //when
        bagService.updateBag(id, bag);

        //then
        verify(bagRepository, times(1)).findById(id);
        verify(bagEntity, times(1)).change(bag);
    }

    @Test
    public void id로_가방을_삭제_할_수_있다(){
        //given
        Long id = 1L;

        //when
        bagService.deleteBag(id);

        //then
        verify(bagRepository, times(1)).deleteById(id);
    }
}
