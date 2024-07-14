package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.HatEntity;
import com.musinsa.shop.infrastructure.entity.SocksEntity;
import com.musinsa.shop.infrastructure.entity.TopEntity;
import com.musinsa.shop.infrastructure.jpa.TopRepository;
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
public class TopRepositoryTests {

    @Autowired
    private TopRepository topepository;

    @Test
    public void 상의에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        TopEntity top = topepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(top);
        assertEquals("C", top.getBrand());
        assertEquals(new BigDecimal(10000).stripTrailingZeros(), top.getPrice().stripTrailingZeros());
    }

    @Test
    public void 상의에서_최고가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        TopEntity top = topepository.findFirstByOrderByPriceDescBrandDesc().get();

        //when
        //then
        assertNotNull(top);
        assertEquals("I", top.getBrand());
        assertEquals(new BigDecimal(11400).stripTrailingZeros(), top.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_상의을_조회_할_수_있다(){
        //given
        TopEntity top1 = TopEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        TopEntity top2 = TopEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        TopEntity top3 = TopEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        topepository.save(top1);
        topepository.save(top2);
        topepository.save(top3);

        String brandName = "A";

        TopEntity top = topepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(top);
        assertEquals("A", top.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                top.getPrice().stripTrailingZeros());
    }

}
