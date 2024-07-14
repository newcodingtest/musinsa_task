package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.domain.Sneakers;
import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.entity.SneakersEntity;
import com.musinsa.shop.infrastructure.jpa.SneakersRepository;
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
public class SneakersRepositoryTests {

    @Autowired
    private SneakersRepository sneakersRepository;


    @Test
    public void 스니커즈에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SneakersEntity sneakers = sneakersRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(sneakers);
        assertEquals("G", sneakers.getBrand());
        assertEquals(new BigDecimal(9000).stripTrailingZeros(), sneakers.getPrice().stripTrailingZeros());
    }

    @Test
    public void 스니커즈에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        SneakersEntity sneakers = sneakersRepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(sneakers);
        assertEquals("E", sneakers.getBrand());
        assertEquals(new BigDecimal(9900).stripTrailingZeros(), sneakers.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_스니커즈를_조회_할_수_있다(){
        //given
        SneakersEntity sneakers1 = SneakersEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        SneakersEntity sneakers2 = SneakersEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        SneakersEntity sneakers3 = SneakersEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        sneakersRepository.save(sneakers1);
        sneakersRepository.save(sneakers2);
        sneakersRepository.save(sneakers3);

        String brandName = "A";

        SneakersEntity sneakers = sneakersRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(sneakers);
        assertEquals("A", sneakers.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                sneakers.getPrice().stripTrailingZeros());
    }

}
