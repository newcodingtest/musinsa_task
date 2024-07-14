package com.musinsa.product.infrastructure.jpa;

import com.musinsa.product.infrastructure.entity.BagEntity;
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
public class BagRepositoryTests {

    @Autowired
    private BagRepository bagRepository;


    @Test
    public void 가방에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag = bagRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(bag);
        assertEquals("A", bag.getBrand());
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }

    @Test
    public void 가방에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        BagEntity bag = bagRepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(bag);
        assertEquals("D", bag.getBrand());
        assertEquals(new BigDecimal(2500).stripTrailingZeros(), bag.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_가방을_조회_할_수_있다(){
        //given
        BagEntity bag1 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        BagEntity bag2 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        BagEntity bag3 = BagEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        bagRepository.save(bag1);
        bagRepository.save(bag2);
        bagRepository.save(bag3);

        String brandName = "A";

        BagEntity bag = bagRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(bag);
        assertEquals("A", bag.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                bag.getPrice().stripTrailingZeros());
    }

}
