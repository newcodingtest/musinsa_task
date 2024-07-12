package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.jpa.OuterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class OuterRepositoryTests {

    @Autowired
    private OuterRepository outerRepository;


    @Test
    public void 아우터에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        OuterEntity outer = outerRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(outer);
        assertEquals("E", outer.getBrand());
        assertEquals(new BigDecimal(5000).stripTrailingZeros(), outer.getPrice().stripTrailingZeros());
    }

}
