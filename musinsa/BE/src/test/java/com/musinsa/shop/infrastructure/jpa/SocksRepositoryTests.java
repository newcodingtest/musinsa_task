package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.jpa.SocksRepository;
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
public class SocksRepositoryTests {

    @Autowired
    private SocksRepository socksRepository;


    @Test
    public void 양말에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SocksEntity socks = socksRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(socks);
        assertEquals("I", socks.getBrand());
        assertEquals(new BigDecimal(1700).stripTrailingZeros(), socks.getPrice().stripTrailingZeros());
    }

    @Test
    public void 양말에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SocksEntity socks = socksRepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(socks);
        assertEquals("D", socks.getBrand());
        assertEquals(new BigDecimal(2400).stripTrailingZeros(), socks.getPrice().stripTrailingZeros());
    }



    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_양말을_조회_할_수_있다(){
        //given
        SocksEntity socks1 = SocksEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        SocksEntity socks2 = SocksEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        SocksEntity socks3 = SocksEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        socksRepository.save(socks1);
        socksRepository.save(socks2);
        socksRepository.save(socks1);

        String brandName = "A";

        SocksEntity socks = socksRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(socks);
        assertEquals("A", socks.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                socks.getPrice().stripTrailingZeros());
    }

}
