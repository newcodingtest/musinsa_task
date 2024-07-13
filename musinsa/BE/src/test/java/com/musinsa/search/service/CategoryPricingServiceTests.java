package com.musinsa.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.search.api.response.SearchResponse;
import com.musinsa.shop.domain.*;
import com.musinsa.shop.infrastructure.jpa.SocksRepository;
import com.musinsa.shop.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryPricingServiceTests {

    @Mock
    private AccessoryService accessoryService;
    @Mock
    private BagService bagService;
    @Mock
    private BottomService bottomService;
    @Mock
    private HatService hatService;
    @Mock
    private OuterService outerService;
    @Mock
    private SneakersService sneakersService;
    @Mock
    private SocksService socksService;
    @Mock
    private TopService topService;

    @InjectMocks
    private CategoryPricingService categoryPricingService;

    @Test
    public void 카테고리_별_최저가격_브랜드와_상품_가격_총액을_조회할_수_있다() {
        //given
        when(accessoryService.getAccessoryMinimumPrice())
                .thenReturn(
                        Accessory.builder()
                                .brand("F")
                                .price(new BigDecimal(1900))
                                .build());

        when(socksService.getAccessoryMinimumPrice())
                .thenReturn(
                        Socks.builder()
                                .brand("I")
                                .price(new BigDecimal(1700))
                                .build());

        when(hatService.getAccessoryMinimumPrice())
                .thenReturn(
                        Hat.builder()
                                .brand("D")
                                .price(new BigDecimal(1500))
                                .build());

        when(bagService.getAccessoryMinimumPrice())
                .thenReturn(
                        Bag.builder()
                                .brand("A")
                                .price(new BigDecimal(2000))
                                .build());

        when(sneakersService.getAccessoryMinimumPrice())
                .thenReturn(
                        Sneakers.builder()
                                .brand("G")
                                .price(new BigDecimal(9000))
                                .build());

        when(bottomService.getAccessoryMinimumPrice())
                .thenReturn(
                        Bottom.builder()
                                .brand("D")
                                .price(new BigDecimal(3000))
                                .build());

        when(outerService.getAccessoryMinimumPrice())
                .thenReturn(
                        Outer.builder()
                                .brand("E")
                                .price(new BigDecimal(5000))
                                .build());

        when(topService.getAccessoryMinimumPrice())
                .thenReturn(
                        Top.builder()
                                .brand("C")
                                .price(new BigDecimal(10_000))
                                .build());

        //when
        SearchResponse searchResponse = categoryPricingService.getMinimumProduct();
        System.out.println(" = " + searchResponse);

        //then

        //카테고리
        assertEquals("상의", searchResponse.getProducts()
                .get(0)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(10_000).stripTrailingZeros(), searchResponse.getProducts()
                .get(0)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("C", searchResponse.getProducts()
                .get(0)
                .getBrand());

        //카테고리
        assertEquals("아우터", searchResponse.getProducts()
                .get(1)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(5000).stripTrailingZeros(), searchResponse.getProducts()
                .get(1)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("E", searchResponse.getProducts()
                .get(1)
                .getBrand());

        //카테고리
        assertEquals("바지", searchResponse.getProducts()
                .get(2)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(3000).stripTrailingZeros(), searchResponse.getProducts()
                .get(2)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("D", searchResponse.getProducts()
                .get(2)
                .getBrand());

        //카테고리
        assertEquals("스니커즈", searchResponse.getProducts()
                .get(3)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(9000).stripTrailingZeros(), searchResponse.getProducts()
                .get(3)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("G", searchResponse.getProducts()
                .get(3)
                .getBrand());

        //카테고리
        assertEquals("가방", searchResponse.getProducts()
                .get(4)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(2000).stripTrailingZeros(), searchResponse.getProducts()
                .get(4)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("A", searchResponse.getProducts()
                .get(4)
                .getBrand());

        //카테고리
        assertEquals("모자", searchResponse.getProducts()
                .get(5)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(1500).stripTrailingZeros(), searchResponse.getProducts()
                .get(5)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("D", searchResponse.getProducts()
                .get(5)
                .getBrand());

        //카테고리
        assertEquals("양말", searchResponse.getProducts()
                .get(6)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(1700).stripTrailingZeros(), searchResponse.getProducts()
                .get(6)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("I", searchResponse.getProducts()
                .get(6)
                .getBrand());

        //카테고리
        assertEquals("액세서리", searchResponse.getProducts()
                .get(7)
                .getCategory());
        //가격
        assertEquals(new BigDecimal(1900).stripTrailingZeros(), searchResponse.getProducts()
                .get(7)
                .getPrice()
                .stripTrailingZeros());
        //브랜드
        assertEquals("F", searchResponse.getProducts()
                .get(7)
                .getBrand());

        //총합
        assertEquals(new BigDecimal(34_100).stripTrailingZeros(), searchResponse.getTotalPrice()
                .stripTrailingZeros());
    }

    @Test
    public void SearchResponse를_JSON으로_변환_할_수_있다() throws JsonProcessingException {
        //given
        ObjectMapper om = new ObjectMapper();

        when(accessoryService.getAccessoryMinimumPrice())
                .thenReturn(
                        Accessory.builder()
                                .brand("F")
                                .price(new BigDecimal(1900))
                                .build());

        when(socksService.getAccessoryMinimumPrice())
                .thenReturn(
                        Socks.builder()
                                .brand("I")
                                .price(new BigDecimal(1700))
                                .build());

        when(hatService.getAccessoryMinimumPrice())
                .thenReturn(
                        Hat.builder()
                                .brand("D")
                                .price(new BigDecimal(1500))
                                .build());

        when(bagService.getAccessoryMinimumPrice())
                .thenReturn(
                        Bag.builder()
                                .brand("A")
                                .price(new BigDecimal(2000))
                                .build());

        when(sneakersService.getAccessoryMinimumPrice())
                .thenReturn(
                        Sneakers.builder()
                                .brand("G")
                                .price(new BigDecimal(9000))
                                .build());

        when(bottomService.getAccessoryMinimumPrice())
                .thenReturn(
                        Bottom.builder()
                                .brand("D")
                                .price(new BigDecimal(3000))
                                .build());

        when(outerService.getAccessoryMinimumPrice())
                .thenReturn(
                        Outer.builder()
                                .brand("E")
                                .price(new BigDecimal(5000))
                                .build());

        when(topService.getAccessoryMinimumPrice())
                .thenReturn(
                        Top.builder()
                                .brand("C")
                                .price(new BigDecimal(10_000))
                                .build());

        //when
        SearchResponse searchResponse = categoryPricingService.getMinimumProduct();

        String jsonInString = om.writeValueAsString(searchResponse);

        System.out.println("jsonInString = " + jsonInString);

    }
}
