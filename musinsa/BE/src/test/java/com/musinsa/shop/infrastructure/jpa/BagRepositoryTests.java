package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.jpa.BagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class BagRepositoryTests {

    @Autowired
    private BagRepository bagRepository;


    @Test
    public void 가방에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag = bagRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(bag);
        assertEquals("A", bag.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }

}
