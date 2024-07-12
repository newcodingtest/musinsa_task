package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.jpa.SneakersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class SneakersRepositoryTests {

    @Autowired
    private SneakersRepository sneakersRepository;


    @Test
    public void 스니커즈에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SneakersEntity sneakers = sneakersRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(sneakers);
        assertEquals("G", sneakers.getBrand());
        assertEquals(new BigDecimal(9000).stripTrailingZeros(), sneakers.getPrice().stripTrailingZeros());
    }

}
