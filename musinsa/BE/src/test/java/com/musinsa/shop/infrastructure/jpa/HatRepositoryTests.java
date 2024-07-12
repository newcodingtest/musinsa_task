package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.jpa.HatRepository;
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
    public void 모자에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        HatEntity hat = hatRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(hat);
        assertEquals("D", hat.getBrand());
        assertEquals(new BigDecimal(1500).stripTrailingZeros(), hat.getPrice().stripTrailingZeros());
    }

}
