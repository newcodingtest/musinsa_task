package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.OuterEntity;
import com.musinsa.shop.infrastructure.jpa.OuterRepository;
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
public class OuterRepositoryTests {

    @Autowired
    private OuterRepository outerRepository;


    @Test
    public void 아우터에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        OuterEntity outer = outerRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(outer);
        assertEquals("E", outer.getBrand());
        assertEquals(new BigDecimal(5000).stripTrailingZeros(), outer.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_아우터를_조회_할_수_있다(){
        //given
        OuterEntity outer1 = OuterEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        OuterEntity outer2 = OuterEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        OuterEntity outer3 = OuterEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        outerRepository.save(outer1);
        outerRepository.save(outer2);
        outerRepository.save(outer3);

        String brandName = "A";

        OuterEntity outer = outerRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(outer);
        assertEquals("A", outer.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                outer.getPrice().stripTrailingZeros());
    }

}
