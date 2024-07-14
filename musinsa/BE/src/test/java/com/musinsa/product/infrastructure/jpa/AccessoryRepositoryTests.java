package com.musinsa.product.infrastructure.jpa;

import com.musinsa.product.infrastructure.entity.AccessoryEntity;
import jakarta.transaction.Transactional;
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
        AccessoryEntity accessory = accessoryRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(accessory);
        assertEquals("F", accessory.getBrand());
        assertEquals(new BigDecimal(1900).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }

    @Test
    public void 액세서리에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        AccessoryEntity accessory = accessoryRepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(accessory);
        assertEquals("I", accessory.getBrand());
        assertEquals(new BigDecimal(2400).stripTrailingZeros(), accessory.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_액세서리를_조회_할_수_있다(){
        //given
        AccessoryEntity accessory1 = AccessoryEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        AccessoryEntity accessory2 = AccessoryEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        AccessoryEntity accessory3 = AccessoryEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        accessoryRepository.save(accessory1);
        accessoryRepository.save(accessory2);

        String brandName = "A";

        //when
        AccessoryEntity accessory = accessoryRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //then
        assertNotNull(accessory);
        assertEquals("A", accessory.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                accessory.getPrice().stripTrailingZeros());
    }

}
