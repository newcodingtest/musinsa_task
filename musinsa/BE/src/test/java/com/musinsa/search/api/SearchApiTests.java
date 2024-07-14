package com.musinsa.search.api;

import com.musinsa.search.api.response.BrandLowestPriceResponse;
import com.musinsa.search.api.response.CategoryLowestPriceResponse;
import com.musinsa.search.api.response.CategoryOneLowestPriceResponse;
import com.musinsa.search.domain.Product;
import com.musinsa.search.service.PricingService;
import com.musinsa.search.utils.BigDecimalUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchApi.class)
public class SearchApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingService pricingService;

    private CategoryLowestPriceResponse categoryLowestPriceResponse;
    private BrandLowestPriceResponse brandLowestPriceResponse;
    private CategoryOneLowestPriceResponse categoryOneLowestPriceResponse;

    public void setup1() {
        // Mock 데이터 설정
        List<Product> mockProducts = List.of(
                Product.builder()
                        .category("모자")
                        .brand("D")
                        .price(new BigDecimal("1500.00"))
                        .build(),
                Product.builder()
                        .category("상의")
                        .brand("C")
                        .price(new BigDecimal("10000.00"))
                        .build(),
                Product.builder()
                        .category("아우터")
                        .brand("E")
                        .price(new BigDecimal("5000.00"))
                        .build(),
                Product.builder()
                        .category("바지")
                        .brand("D")
                        .price(new BigDecimal("3000.00"))
                        .build(),
                Product.builder()
                        .category("스니커즈")
                        .brand("G")
                        .price(new BigDecimal("9000.00"))
                        .build(),
                Product.builder()
                        .category("가방")
                        .brand("A")
                        .price(new BigDecimal("2000.00"))
                        .build(),
                Product.builder()
                        .category("양말")
                        .brand("I")
                        .price(new BigDecimal("1700.00"))
                        .build(),
                Product.builder()
                        .category("액세서리")
                        .brand("F")
                        .price(new BigDecimal("1900.00"))
                        .build()
        );


        categoryLowestPriceResponse = CategoryLowestPriceResponse.builder()
                .products(CategoryLowestPriceResponse.fromModel(mockProducts))
                .totalPrice(BigDecimalUtils.formatWithCommas(new BigDecimal("34100.00")))
                .build();

        List<BrandLowestPriceResponse.CategoryPrice> categoryPrices = mockProducts.stream()
                .map(BrandLowestPriceResponse.CategoryPrice::fromModel)
                .collect(Collectors.toList());

        brandLowestPriceResponse = BrandLowestPriceResponse.builder()
                .lowestProducts(BrandLowestPriceResponse.LowestProduct.builder()
                        .brand("D")
                        .categoryPrices(categoryPrices)
                        .build())
                .totalPrice(BigDecimalUtils.formatWithCommas(new BigDecimal("34100.00")))
                .build();

        categoryOneLowestPriceResponse = CategoryOneLowestPriceResponse.builder()
                .category("모자")
                .lowest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("D")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("1500.00")))
                        .build())
                .highest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("E")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("5000.00")))
                        .build())
                .build();

        // Mock 서비스 메서드 설정
        given(pricingService.getMinimumProduct()).willReturn(categoryLowestPriceResponse);
        given(pricingService.getLowestBrandProduct()).willReturn(brandLowestPriceResponse);
        given(pricingService.getLowHigtestBrandPrice(Mockito.anyString())).willReturn(categoryOneLowestPriceResponse);
    }

    public void setup2() {
        // Mock 데이터 설정
        List<Product> mockProducts = List.of(
                Product.builder()
                        .category("모자")
                        .brand("D")
                        .price(new BigDecimal("1500.00"))
                        .build(),
                Product.builder()
                        .category("상의")
                        .brand("C")
                        .price(new BigDecimal("10100.00"))
                        .build(),
                Product.builder()
                        .category("아우터")
                        .brand("E")
                        .price(new BigDecimal("5100.00"))
                        .build(),
                Product.builder()
                        .category("바지")
                        .brand("D")
                        .price(new BigDecimal("3000.00"))
                        .build(),
                Product.builder()
                        .category("양말")
                        .brand("I")
                        .price(new BigDecimal("2400.00"))
                        .build(),
                Product.builder()
                        .category("스니커즈")
                        .brand("G")
                        .price(new BigDecimal("9500.00"))
                        .build(),
                Product.builder()
                        .category("액세서리")
                        .brand("F")
                        .price(new BigDecimal("2000.00"))
                        .build(),
                Product.builder()
                        .category("가방")
                        .brand("A")
                        .price(new BigDecimal("2500.00"))
                        .build()
        );

        categoryLowestPriceResponse = CategoryLowestPriceResponse.builder()
                .products(CategoryLowestPriceResponse.fromModel(mockProducts))
                .totalPrice(BigDecimalUtils.formatWithCommas(new BigDecimal("36100.00")))
                .build();

        List<BrandLowestPriceResponse.CategoryPrice> categoryPrices = List.of(
                BrandLowestPriceResponse.CategoryPrice.builder().category("모자").price("1,500").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("상의").price("10,100").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("아우터").price("5,100").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("바지").price("3,000").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("양말").price("2,400").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("스니커즈").price("9,500").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("액세서리").price("2,000").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("가방").price("2,500").build()
        );

        brandLowestPriceResponse = BrandLowestPriceResponse.builder()
                .lowestProducts(BrandLowestPriceResponse.LowestProduct.builder()
                        .brand("D")
                        .categoryPrices(categoryPrices)
                        .build())
                .totalPrice("36,100")
                .build();

        categoryOneLowestPriceResponse = CategoryOneLowestPriceResponse.builder()
                .category("모자")
                .lowest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("D")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("1500.00")))
                        .build())
                .highest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("E")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("5100.00")))
                        .build())
                .build();

        // Mock 서비스 메서드 설정
        given(pricingService.getMinimumProduct()).willReturn(categoryLowestPriceResponse);
        given(pricingService.getLowestBrandProduct()).willReturn(brandLowestPriceResponse);
        given(pricingService.getLowHigtestBrandPrice(Mockito.anyString())).willReturn(categoryOneLowestPriceResponse);
    }

    public void setup3() {
        // Mock 데이터 설정
        List<Product> mockProducts = List.of(
                Product.builder()
                        .category("모자")
                        .brand("D")
                        .price(new BigDecimal("1500.00"))
                        .build(),
                Product.builder()
                        .category("상의")
                        .brand("C")
                        .price(new BigDecimal("10000.00"))
                        .build(),
                Product.builder()
                        .category("아우터")
                        .brand("E")
                        .price(new BigDecimal("5100.00"))
                        .build(),
                Product.builder()
                        .category("바지")
                        .brand("D")
                        .price(new BigDecimal("3000.00"))
                        .build(),
                Product.builder()
                        .category("양말")
                        .brand("I")
                        .price(new BigDecimal("2400.00"))
                        .build(),
                Product.builder()
                        .category("스니커즈")
                        .brand("G")
                        .price(new BigDecimal("9500.00"))
                        .build(),
                Product.builder()
                        .category("액세서리")
                        .brand("F")
                        .price(new BigDecimal("2000.00"))
                        .build(),
                Product.builder()
                        .category("가방")
                        .brand("A")
                        .price(new BigDecimal("2500.00"))
                        .build()
        );

        categoryLowestPriceResponse = CategoryLowestPriceResponse.builder()
                .products(CategoryLowestPriceResponse.fromModel(mockProducts))
                .totalPrice(BigDecimalUtils.formatWithCommas(new BigDecimal("36100.00")))
                .build();

        List<BrandLowestPriceResponse.CategoryPrice> categoryPrices = List.of(
                BrandLowestPriceResponse.CategoryPrice.builder().category("모자").price("1,500").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("상의").price("10,100").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("아우터").price("5,100").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("바지").price("3,000").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("양말").price("2,400").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("스니커즈").price("9,500").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("액세서리").price("2,000").build(),
                BrandLowestPriceResponse.CategoryPrice.builder().category("가방").price("2,500").build()
        );

        brandLowestPriceResponse = BrandLowestPriceResponse.builder()
                .lowestProducts(BrandLowestPriceResponse.LowestProduct.builder()
                        .brand("D")
                        .categoryPrices(categoryPrices)
                        .build())
                .totalPrice("36,100")
                .build();

        categoryOneLowestPriceResponse = CategoryOneLowestPriceResponse.builder()
                .category("상의")
                .lowest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("C")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("10000.00")))
                        .build())
                .highest(CategoryOneLowestPriceResponse.DetailInfo.builder()
                        .brand("I")
                        .price(BigDecimalUtils.formatWithCommas(new BigDecimal("11400.00")))
                        .build())
                .build();

        // Mock 서비스 메서드 설정
        given(pricingService.getMinimumProduct()).willReturn(categoryLowestPriceResponse);
        given(pricingService.getLowestBrandProduct()).willReturn(brandLowestPriceResponse);
        given(pricingService.getLowHigtestBrandPrice(Mockito.anyString())).willReturn(categoryOneLowestPriceResponse);
    }


    @DisplayName("카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")
    @Test
    public void testGetLowestPriceCategoryProduct() throws Exception {
        //given
        setup1();
        String expectedJson = "{ " +
                "\"products\": [" +
                "{ \"카테고리\": \"모자\", \"브랜드\": \"D\", \"가격\": \"1,500\" }," +
                "{ \"카테고리\": \"상의\", \"브랜드\": \"C\", \"가격\": \"10,000\" }," +
                "{ \"카테고리\": \"아우터\", \"브랜드\": \"E\", \"가격\": \"5,000\" }," +
                "{ \"카테고리\": \"바지\", \"브랜드\": \"D\", \"가격\": \"3,000\" }," +
                "{ \"카테고리\": \"스니커즈\", \"브랜드\": \"G\", \"가격\": \"9,000\" }," +
                "{ \"카테고리\": \"가방\", \"브랜드\": \"A\", \"가격\": \"2,000\" }," +
                "{ \"카테고리\": \"양말\", \"브랜드\": \"I\", \"가격\": \"1,700\" }," +
                "{ \"카테고리\": \"액세서리\", \"브랜드\": \"F\", \"가격\": \"1,900\" }" +
                "], " +
                "\"총액\": \"34,100\" }";

        mockMvc.perform(get("/api/cheapest-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().json(expectedJson));
    }

    @DisplayName("단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API")
    @Test
    public void testGetLowestPriceBrandProduct() throws Exception {
        setup2();
        String expectedJson = "{ " +
                "\"최저가\": { " +
                "\"브랜드\": \"D\", " +
                "\"카테고리\": [" +
                "{ \"카테고리\": \"모자\", \"가격\": \"1,500\" }," +
                "{ \"카테고리\": \"상의\", \"가격\": \"10,100\" }," +
                "{ \"카테고리\": \"아우터\", \"가격\": \"5,100\" }," +
                "{ \"카테고리\": \"바지\", \"가격\": \"3,000\" }," +
                "{ \"카테고리\": \"양말\", \"가격\": \"2,400\" }," +
                "{ \"카테고리\": \"스니커즈\", \"가격\": \"9,500\" }," +
                "{ \"카테고리\": \"액세서리\", \"가격\": \"2,000\" }," +
                "{ \"카테고리\": \"가방\", \"가격\": \"2,500\" }" +
                "] }, " +
                "\"총액\": \"36,100\" }";

        mockMvc.perform(get("/api/cheapest-brand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().json(expectedJson));
    }

    @DisplayName("카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")
    @Test
    public void testGetLowestPriceBrandProductByCategory() throws Exception {
        //given
        setup3();
        String expectedJson = "{ " +
                "\"카테고리\": \"상의\", " +
                "\"최저가\": { \"브랜드\": \"C\", \"가격\": \"10,000\" }, " +
                "\"최고가\": { \"브랜드\": \"I\", \"가격\": \"11,400\" } }";

        //when then
        String category = "top";
        mockMvc.perform(get("/api/cheapest-hightest")
                        .param("category", category)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(content().json(expectedJson));
    }

}
