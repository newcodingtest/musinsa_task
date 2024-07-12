package com.musinsa.shop.service;

import com.musinsa.shop.domain.Accessory;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.jpa.AccessoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTests {

    @Mock
    private AccessoryRepository accessoryRepository;

    @InjectMocks
    private AccessoryService accessoryService;

    @Test
    public void 액세서리에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        AccessoryEntity accessory1 = AccessoryEntity.builder()
                .brand("F")
                .price(new BigDecimal(1900))
                .build();


        AccessoryEntity accessory2 = AccessoryEntity.builder()
                .brand("G")
                .price(new BigDecimal(2000))
                .build();

        when(accessoryRepository.findFirstByOrderBrandDesc())
                                .thenReturn(Optional.of(accessory1));

        //when
        Accessory accessory = accessoryService.getAccessoryMinimumPrice();


        //then
        assertNotNull(accessory);
        assertEquals("F", accessory.getBrand());
        assertEquals(new BigDecimal(1900).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }


    @Test
    public void testGetAccessoryMinimumPrice_noData() {
        // Given
        when(accessoryRepository.findTopByOrderByPriceAsc()).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            accessoryService.getAccessoryMinimumPrice();
        });
        assertEquals("No accessories found", exception.getMessage());
    }
}
