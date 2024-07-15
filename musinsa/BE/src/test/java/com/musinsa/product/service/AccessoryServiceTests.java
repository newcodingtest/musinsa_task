package com.musinsa.product.service;

import com.musinsa.common.exception.CategoryErrorCode;
import com.musinsa.product.domain.Accessory;
import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import com.musinsa.product.infrastructure.jpa.AccessoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTests {

    @Mock
    private AccessoryRepository accessoryRepository;

    @InjectMocks
    private AccessoryService accessoryService;

    @Mock
    private AccessoryEntity accessoryEntity;


    @Test
    public void 액세서리의_최저가격_브랜드와_가격을_조회할_수_있다(){
        //given
        AccessoryEntity accessory1 = AccessoryEntity.builder()
                .brand("F")
                .price(new BigDecimal(1900))
                .build();

        AccessoryEntity accessory2 = AccessoryEntity.builder()
                .brand("G")
                .price(new BigDecimal(2000))
                .build();

        when(accessoryRepository.findFirstByOrderByPriceAscBrandDesc())
                                .thenReturn(Optional.of(accessory1));

        //when
        Accessory accessory = accessoryService.getAccessoryMinimumPrice();

        //then
        assertNotNull(accessory);
        assertEquals("F", accessory.getBrand());
        assertEquals(new BigDecimal(1900).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }

    @Test
    public void 액세서리의_최고가격_브랜드와_가격을_조회할_수_있다(){
        //given
        AccessoryEntity accessory1 = AccessoryEntity.builder()
                .brand("F")
                .price(new BigDecimal(1900))
                .build();

        AccessoryEntity accessory2 = AccessoryEntity.builder()
                .brand("G")
                .price(new BigDecimal(2000))
                .build();

        when(accessoryRepository.findFirstByOrderByPriceDescBrandDesc())
                .thenReturn(Optional.of(accessory2));

        //when
        Accessory accessory = accessoryService.getAccessoryMaximumPrice();

        //then
        assertNotNull(accessory);
        assertEquals("G", accessory.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }

    @Test
    public void 액세서리_정보가_없으면_최저가격_브랜드와_가격을_조회할_수_없다() {
        //given
        when(accessoryRepository.findFirstByOrderByPriceAscBrandDesc())
                .thenReturn(Optional.empty());

        //when
        //then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accessoryService.getAccessoryMinimumPrice();
        });

        assertEquals(CategoryErrorCode
                        .CATEGORY_ITEM_NOT_FOUND
                        .getMessage(),
                exception.getMessage());
    }

    @Test
    public void 새로운_액세서리를_생성_할_수_있다() {
        //given
         Accessory accessory = Accessory.builder()
                 .brand("액세서리")
                 .price(new BigDecimal(0))
                 .build();

        AccessoryEntity accessoryEntity = AccessoryEntity.fromModel(accessory);

        //when
        accessoryService.createAccssory(accessory);

        //then
        verify(accessoryRepository, times(1)).save(any(AccessoryEntity.class));
    }

    @Test
    public void 액세서리를_수정_할_수_있다(){
        Long id = 1L;
        //given
        Accessory accessory = Accessory.builder()
                .brand("액세서리")
                .price(new BigDecimal(0))
                .build();

        when(accessoryRepository.findById(id)).thenReturn(Optional.of(accessoryEntity));

        //when
        accessoryService.updateAccssory(id, accessory);

        //then
        verify(accessoryRepository, times(1)).findById(id);
        verify(accessoryEntity, times(1)).change(accessory);
    }

    @Test
    public void id로_액세서리를_삭제_할_수_있다(){
        //given
        Long id = 1L;

        //when
        accessoryService.deleteAccssory(id);

        //then
        verify(accessoryRepository, times(1)).deleteById(id);
    }


}
