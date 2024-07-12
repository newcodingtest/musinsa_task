package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.TopEntity;
import com.musinsa.shop.infrastructure.jpa.TopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class TopRepositoryTests {

    @Autowired
    private TopRepository topepository;


    @Test
    public void 상의에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        TopEntity top = topepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(top);
        assertEquals("C", top.getBrand());
        assertEquals(new BigDecimal(10000).stripTrailingZeros(), top.getPrice().stripTrailingZeros());
    }

}
