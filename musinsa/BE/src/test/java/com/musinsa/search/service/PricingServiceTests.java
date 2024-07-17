package com.musinsa.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.common.utils.RequestUtils;
import com.musinsa.search.api.response.BrandLowestPriceResponse;
import com.musinsa.search.api.response.CategoryLowestPriceResponse;
import com.musinsa.search.api.response.CategoryOneLowestPriceResponse;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTests {

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
    private PricingService categoryPricingService;

    @Test
    public void 카테고리_별_최저가격_브랜드와_상품_가격_총액을_조회할_수_있다() {
        //given
        when(accessoryService.getAccessoryMinimumPrice())
                .thenReturn(
                        Accessory.builder()
                                .brand("F")
                                .price(new BigDecimal(1900))
                                .build());

        when(socksService.getSocksMinimumPrice())
                .thenReturn(
                        Socks.builder()
                                .brand("I")
                                .price(new BigDecimal(1700))
                                .build());

        when(hatService.getHatMinimumPrice())
                .thenReturn(
                        Hat.builder()
                                .brand("D")
                                .price(new BigDecimal(1500))
                                .build());

        when(bagService.getBagMinimumPrice())
                .thenReturn(
                        Bag.builder()
                                .brand("A")
                                .price(new BigDecimal(2000))
                                .build());

        when(sneakersService.getSneakersMinimumPrice())
                .thenReturn(
                        Sneakers.builder()
                                .brand("G")
                                .price(new BigDecimal(9000))
                                .build());

        when(bottomService.getBottomMinimumPrice())
                .thenReturn(
                        Bottom.builder()
                                .brand("D")
                                .price(new BigDecimal(3000))
                                .build());

        when(outerService.getOuterMinimumPrice())
                .thenReturn(
                        Outer.builder()
                                .brand("E")
                                .price(new BigDecimal(5000))
                                .build());

        when(topService.getTopMinimumPrice())
                .thenReturn(
                        Top.builder()
                                .brand("C")
                                .price(new BigDecimal(10_000))
                                .build());

        //when
        CategoryLowestPriceResponse categoryLowestPriceResponse = categoryPricingService.getMinimumProduct();

        //then

        //카테고리
        assertEquals("모자", categoryLowestPriceResponse.getProducts()
                .get(0).getCategory());
        //가격
        assertEquals("1,500", categoryLowestPriceResponse.getProducts()
                .get(0)
                .getPrice());
        //브랜드
        assertEquals("D", categoryLowestPriceResponse.getProducts()
                .get(0)
                .getBrand());

        //카테고리
        assertEquals("상의", categoryLowestPriceResponse.getProducts()
                .get(1)
                .getCategory());
        //가격
        assertEquals("10,000", categoryLowestPriceResponse.getProducts()
                .get(1)
                .getPrice());
        //브랜드
        assertEquals("C", categoryLowestPriceResponse.getProducts()
                .get(1)
                .getBrand());

        //카테고리
        assertEquals("아우터", categoryLowestPriceResponse.getProducts()
                .get(2)
                .getCategory());
        //가격
        assertEquals("5,000", categoryLowestPriceResponse.getProducts()
                .get(2)
                .getPrice());
        //브랜드
        assertEquals("E", categoryLowestPriceResponse.getProducts()
                .get(2)
                .getBrand());

        //카테고리
        assertEquals("바지", categoryLowestPriceResponse.getProducts()
                .get(3)
                .getCategory());
        //가격
        assertEquals("3,000", categoryLowestPriceResponse.getProducts()
                .get(3)
                .getPrice());
        //브랜드
        assertEquals("D", categoryLowestPriceResponse.getProducts()
                .get(3)
                .getBrand());

        //카테고리
        assertEquals("스니커즈", categoryLowestPriceResponse.getProducts()
                .get(4)
                .getCategory());
        //가격
        assertEquals("9,000", categoryLowestPriceResponse.getProducts()
                .get(4)
                .getPrice());
        //브랜드
        assertEquals("G", categoryLowestPriceResponse.getProducts()
                .get(4)
                .getBrand());

        //카테고리
        assertEquals("가방", categoryLowestPriceResponse.getProducts()
                .get(5)
                .getCategory());
        //가격
        assertEquals("2,000", categoryLowestPriceResponse.getProducts()
                .get(5)
                .getPrice());
        //브랜드
        assertEquals("A", categoryLowestPriceResponse.getProducts()
                .get(5)
                .getBrand());

        //카테고리
        assertEquals("양말", categoryLowestPriceResponse.getProducts()
                .get(6)
                .getCategory());
        //가격
        assertEquals("1,700", categoryLowestPriceResponse.getProducts()
                .get(6)
                .getPrice());
        //브랜드
        assertEquals("I", categoryLowestPriceResponse.getProducts()
                .get(6)
                .getBrand());

        //카테고리
        assertEquals("액세서리", categoryLowestPriceResponse.getProducts()
                .get(7)
                .getCategory());
        //가격
        assertEquals("1,900", categoryLowestPriceResponse.getProducts()
                .get(7)
                .getPrice());
        //브랜드
        assertEquals("F", categoryLowestPriceResponse.getProducts()
                .get(7)
                .getBrand());

        //총합
        assertEquals("34,100",
                categoryLowestPriceResponse.getTotalPrice());
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

        when(socksService.getSocksMinimumPrice())
                .thenReturn(
                        Socks.builder()
                                .brand("I")
                                .price(new BigDecimal(1700))
                                .build());

        when(hatService.getHatMinimumPrice())
                .thenReturn(
                        Hat.builder()
                                .brand("D")
                                .price(new BigDecimal(1500))
                                .build());

        when(bagService.getBagMinimumPrice())
                .thenReturn(
                        Bag.builder()
                                .brand("A")
                                .price(new BigDecimal(2000))
                                .build());

        when(sneakersService.getSneakersMinimumPrice())
                .thenReturn(
                        Sneakers.builder()
                                .brand("G")
                                .price(new BigDecimal(9000))
                                .build());

        when(bottomService.getBottomMinimumPrice())
                .thenReturn(
                        Bottom.builder()
                                .brand("D")
                                .price(new BigDecimal(3000))
                                .build());

        when(outerService.getOuterMinimumPrice())
                .thenReturn(
                        Outer.builder()
                                .brand("E")
                                .price(new BigDecimal(5000))
                                .build());

        when(topService.getTopMinimumPrice())
                .thenReturn(
                        Top.builder()
                                .brand("C")
                                .price(new BigDecimal(10_000))
                                .build());

        //when
        CategoryLowestPriceResponse categoryLowestPriceResponse = categoryPricingService.getMinimumProduct();

        String jsonInString = om.writeValueAsString(categoryLowestPriceResponse);

    }

    public void 브랜드별_최저가격_상품_세팅(){
        List<String> brand = List.of("A","B","C","D","E","F","G","H","I");

        for (int i=0; i<brand.size(); i++){
            Hat hat = Hat.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(hatService.getHatMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(hat);

            Top top = Top.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(topService.getTopMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(top);

            Outer outer = Outer.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(outerService.getOuterMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(outer);

            Bottom bottom = Bottom.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(bottomService.getBottomMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(bottom);

            Socks socks = Socks.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(socksService.getSocksMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(socks);

            Sneakers sneakers = Sneakers.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(sneakersService.getSneakersMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(sneakers);

            Accessory accessory = Accessory.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(accessoryService.getAccessoryMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(accessory);

            Bag bag = Bag.builder()
                    .brand(brand.get(i))
                    .price(new BigDecimal(1000+i))
                    .build();

            when(bagService.getBagMinimumPriceByBrand(brand.get(i)))
                    .thenReturn(bag);
        }
    }



    @Test
    public void 가장_싼_브랜드의_상품을_조회_할_수_있다() {
        //given
        브랜드별_최저가격_상품_세팅();

        //when
        BrandLowestPriceResponse categoryLowestPriceResponse = categoryPricingService.getLowestBrandProduct();

        //then
        assertEquals("8,000", categoryLowestPriceResponse.getTotalPrice());
        assertEquals("A", categoryLowestPriceResponse
                .getLowestProducts()
                .getBrand());
    }


    public void 카테고리_최저최고_상품_세팅(String category){
        List<String> brand = List.of("A","B","C","D","E","F","G","H","I");

        for (int i=0; i<brand.size(); i++){
            if (category.equals(RequestUtils.HAT)){
                Hat hatMin = Hat.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Hat hatMax = Hat.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(hatService.getHatMinimumPrice())
                        .thenReturn(hatMin);
                when(hatService.getHatMaximumPrice())
                        .thenReturn(hatMax);
            } else if(category.equals(RequestUtils.TOP)){
                Top topMin = Top.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Top topMax = Top.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(topService.getTopMinimumPrice())
                        .thenReturn(topMin);
                when(topService.getTopMaximumPrice())
                        .thenReturn(topMax);
            } else if(category.equals(RequestUtils.OUTER)){
                Outer outerMin = Outer.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Outer outerMax = Outer.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(outerService.getOuterMinimumPrice())
                        .thenReturn(outerMin);
                when(outerService.getOuterMaximumPrice())
                        .thenReturn(outerMax);
            } else if(category.equals(RequestUtils.BOTTOM)){
                Bottom bottomMin = Bottom.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Bottom bottomMax = Bottom.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(bottomService.getBottomMinimumPrice())
                        .thenReturn(bottomMin);

                when(bottomService.getBottomMaximumPrice())
                        .thenReturn(bottomMax);
            } else if(category.equals(RequestUtils.SOCKS)){
                Socks socksMin = Socks.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Socks socksMax = Socks.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(socksService.getSocksMinimumPrice())
                        .thenReturn(socksMin);
                when(socksService.getSocksMaximumPrice())
                        .thenReturn(socksMax);
            } else if(category.equals(RequestUtils.SNEAKERS)){
                Sneakers sneakersMin = Sneakers.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Sneakers sneakersMax = Sneakers.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();
                when(sneakersService.getSneakersMinimumPrice())
                        .thenReturn(sneakersMin);
                when(sneakersService.getSneakersMaximumPrice())
                        .thenReturn(sneakersMax);
            } else if(category.equals(RequestUtils.BAG)){
                Accessory accessoryMin = Accessory.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Accessory accessoryMax = Accessory.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(accessoryService.getAccessoryMinimumPrice())
                        .thenReturn(accessoryMin);
                when(accessoryService.getAccessoryMaximumPrice())
                        .thenReturn(accessoryMax);
            } else if(category.equals(RequestUtils.BAG)){
                Bag bagMin = Bag.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(1000+i))
                        .build();

                Bag bagMax = Bag.builder()
                        .brand(brand.get(i))
                        .price(new BigDecimal(2000+i))
                        .build();

                when(bagService.getBagMinimumPrice())
                        .thenReturn(bagMin);
                when(bagService.getBagMaximumPrice())
                        .thenReturn(bagMax);
            }
        }
    }


    @Test
    public void 카테고리_이름으로_최저_최고가격을_조회_할_수_있다() {
        //given
        String category = RequestUtils.TOP;
        카테고리_최저최고_상품_세팅(category);
        
        //when
        CategoryOneLowestPriceResponse getLowHigtestBrandPrice = categoryPricingService
                .getLowHigtestBrandPrice(category);


        //then
        assertEquals("상의", getLowHigtestBrandPrice.getCategory());
        assertEquals("I", getLowHigtestBrandPrice
                .getLowest()
                .getBrand());
        assertEquals("1,008", getLowHigtestBrandPrice
                .getLowest()
                .getPrice());
        assertEquals("I", getLowHigtestBrandPrice
                .getHighest()
                .getBrand());
        assertEquals("2,008", getLowHigtestBrandPrice
                .getHighest()
                .getPrice());
    }


}
