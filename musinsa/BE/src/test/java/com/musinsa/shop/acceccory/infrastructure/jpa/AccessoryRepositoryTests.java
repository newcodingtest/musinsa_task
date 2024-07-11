package com.musinsa.shop.acceccory.infrastructure.jpa;

import com.musinsa.shop.accessory.infrastructure.entity.Accessory;
import com.musinsa.shop.accessory.infrastructure.jpa.AccessoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class AccessoryRepositoryTests {

    @Autowired
    private AccessoryRepository accessoryRepository;


    @Test
    public void 액세서리에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        Accessory accessory = accessoryRepository.findFirstByOrderByPriceAsc();

        //when
        //then
        assertNotNull(accessory);
        assertEquals("F", accessory.getBrand());
        assertEquals(new BigDecimal(1900).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }

}
