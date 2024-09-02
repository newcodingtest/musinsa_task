package com.musinsa.shop.api;

import com.musinsa.shop.api.response.ItemResponse;
import com.musinsa.shop.api.response.LowestPricedItems;
import com.musinsa.shop.dto.query.ProductQueryDto;
import com.musinsa.shop.infrastructure.entity.ClothingCategory;
import com.musinsa.shop.service.query.ProductQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductSearchApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductQueryService productQueryService;


    @Test
    public void testFindLowestPricedItemsByCategory() throws Exception {
        //given
        List<ProductQueryDto> mockItems = Arrays.asList(
                new ProductQueryDto(ClothingCategory.BAG, "A", BigDecimal.valueOf(10000.00)),
                new ProductQueryDto(ClothingCategory.ACCESSORY, "B", BigDecimal.valueOf(6000.00)),
                new ProductQueryDto(ClothingCategory.OUTER, "C", BigDecimal.valueOf(4000.00))
        );

        when(productQueryService.getLowerItems())
                .thenReturn(mockItems);

        List<ItemResponse> expectedItems = mockItems.stream()
                .map(ItemResponse::fromModel)
                .collect(Collectors.toList());
        BigDecimal expectedTotalPrice = mockItems.stream()
                .map(ProductQueryDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LowestPricedItems expectedResponse = LowestPricedItems.builder()
                .items(expectedItems)
                .totalPrice(expectedTotalPrice.toString())
                .build();

        //then
        mockMvc.perform(get("/api/products/lowest-price"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.totalPrice").value(expectedTotalPrice.toString()))
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.items[0].price").value(expectedItems.get(0).getPrice().toString()))
                .andExpect(jsonPath("$.items[1].price").value(expectedItems.get(1).getPrice().toString()))
                .andExpect(jsonPath("$.items[2].price").value(expectedItems.get(2).getPrice().toString()));
    }
}