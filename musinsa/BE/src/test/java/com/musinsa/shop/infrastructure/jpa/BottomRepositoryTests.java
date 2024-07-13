package com.musinsa.shop.infrastructure.jpa;

import com.musinsa.shop.infrastructure.entity.AccessoryEntity;
import com.musinsa.shop.infrastructure.entity.BagEntity;
import com.musinsa.shop.infrastructure.entity.BottomEntity;
import com.musinsa.shop.infrastructure.jpa.BottomRepository;
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
public class BottomRepositoryTests {

    @Autowired
    private BottomRepository bottomRepository;


    @Test
    public void 바지에서_최저가격의_브랜드와_가격을_조회할_수_있다(){
        //given
        BottomEntity bottom = bottomRepository.findFirstByOrderByPriceAscBrandDesc().get();

        //when
        //then
        assertNotNull(bottom);
        assertEquals("D", bottom.getBrand());
        assertEquals(new BigDecimal(3000).stripTrailingZeros(), bottom.getPrice().stripTrailingZeros());
    }

    @Transactional
    @Test
    public void 특정_브랜드의_최저가격_바지를_조회_할_수_있다(){
        //given
        BottomEntity bottom1 = BottomEntity.builder()
                .brand("A")
                .price(new BigDecimal(1))
                .build();

        BottomEntity bottom2 = BottomEntity.builder()
                .brand("A")
                .price(new BigDecimal(2))
                .build();

        BottomEntity bottom3 = BottomEntity.builder()
                .brand("A")
                .price(new BigDecimal(3))
                .build();

        bottomRepository.save(bottom1);
        bottomRepository.save(bottom2);
        bottomRepository.save(bottom3);

        String brandName = "A";

        BottomEntity bottom = bottomRepository.findFirstByBrandOrderByPriceAsc(brandName).get();

        //when
        //then
        assertNotNull(bottom);
        assertEquals("A", bottom.getBrand());
        assertEquals(new BigDecimal(1).stripTrailingZeros(),
                bottom.getPrice().stripTrailingZeros());
    }

}
