package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.jpa.HatRepository;
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
public class HatRepositoryTests {

    @Autowired
    private HatRepository hatRepository;


    @Test
    public void 모자_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        HatEntity hat = hatRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(hat);
        assertEquals("D", hat.getBrand());
        assertEquals(new BigDecimal(1500).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }

    @Test
    public void 모자에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        HatEntity hat = hatRepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(hat);
        assertEquals("B", hat.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_모자를_조회_할_수_있다(){
        //given
        HatEntity hat1 = HatEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        HatEntity hat2 = HatEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        HatEntity hat3 = HatEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        hatRepository.save(hat1);
        hatRepository.save(hat2);
        hatRepository.save(hat3);

        String brandName = "A";

        HatEntity hat = hatRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(hat);
        assertEquals("A", hat.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                hat.getPrice().stripTrailingZeros());
    }

}
