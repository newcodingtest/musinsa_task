package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.jpa.SocksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class SocksRepositoryTests {

    @Autowired
    private SocksRepository socksRepository;


    @Test
    public void 양말에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SocksEntity socks = socksRepository.findFirstByOrderByPriceAscBrandDesc();

        //when
        //then
        assertNotNull(socks);
        assertEquals("I", socks.getBrand());
        assertEquals(new BigDecimal(1700).stripTrailingZeros(), socks.getPrice().stripTrailingZeros());
    }

}
