package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.jpa.BottomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class BottomRepositoryTests {

    @Autowired
    private BottomRepository bottomRepository;


    @Test
    public void 바지에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        BottomEntity bottom = bottomRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(bottom);
        assertEquals("D", bottom.getBrand());
        assertEquals(new BigDecimal(3000).stripTrailingZeros(), bottom.getPrice().stripTrailingZeros());
    }

}
