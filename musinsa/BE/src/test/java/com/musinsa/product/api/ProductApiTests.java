package com.musinsa.product.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductDeleteRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.facade.ProductInventoryFacade;

import com.musinsa.product.service.TopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductApi.class)
public class ProductApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<ProductCreateRequest> createRequestCaptor;
    @Captor
    private ArgumentCaptor<ProductUpdateRequest> updateRequestCaptor;

    @Captor
    private ArgumentCaptor<ProductDeleteRequest> deleteRequestCaptor;
    @MockBean
    private ProductInventoryFacade productInventoryFacade;

    @MockBean
    private TopService topService;

    private ProductCreateRequest createRequest;
    private ProductDeleteRequest deleteRequest;
    private ProductUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        createRequest = ProductCreateRequest.builder()
                .brand("A")
                .price(new BigDecimal(10000))
                .category(RequestUtils.TOP)
                .build();

        updateRequest = ProductUpdateRequest.builder()
                .id(1L)
                .brand("A")
                .price(new BigDecimal(10000))
                .category(RequestUtils.TOP)
                .build();

        deleteRequest = ProductDeleteRequest.builder()
                .category(RequestUtils.TOP)
                .id(1L)
                .build();
    }

    @Test
    void 상품을_생성_할_수_있다() throws Exception {
        // given
        String request = new ObjectMapper().writeValueAsString(createRequest);
        Mockito.doNothing().when(productInventoryFacade).createProduct(Mockito.any(ProductCreateRequest.class));
        // when
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // then
                .andExpect(status().isCreated());

        Mockito.verify(productInventoryFacade).createProduct(createRequestCaptor.capture());
        assertEquals(createRequest.getBrand(), createRequestCaptor.getValue().getBrand());
        assertEquals(createRequest.getCategory(), createRequestCaptor.getValue().getCategory());
        assertEquals(createRequest.getPrice(), createRequestCaptor.getValue().getPrice());
    }

    @Test
    void 상품을_삭제_할_수_있다() throws Exception {
        // given
        String request = new ObjectMapper().writeValueAsString(deleteRequest);
        Mockito.doNothing().when(productInventoryFacade).deleteProduct(Mockito.any(ProductDeleteRequest.class));

        // when
        mockMvc.perform(delete("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                // then
                .andExpect(status().isOk());

        Mockito.verify(productInventoryFacade).deleteProduct(deleteRequestCaptor.capture());
        assertEquals(deleteRequest.getId(), deleteRequestCaptor.getValue().getId());
        assertEquals(deleteRequest.getCategory(), deleteRequestCaptor.getValue().getCategory());

    }

    @Test
    void 상품을_수정_할_수_있다() throws Exception {
        // given
        String request = new ObjectMapper().writeValueAsString(updateRequest);
        Mockito.doNothing().when(productInventoryFacade).updateProduct(Mockito.any(ProductUpdateRequest.class));

        // when
        mockMvc.perform(put("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                // then
                .andExpect(status().isOk());

        Mockito.verify(productInventoryFacade).updateProduct(updateRequestCaptor.capture());
        assertEquals(updateRequest.getId(), updateRequestCaptor.getValue().getId());
        assertEquals(updateRequest.getCategory(), updateRequestCaptor.getValue().getCategory());
        assertEquals(updateRequest.getBrand(), updateRequestCaptor.getValue().getBrand());
        assertEquals( updateRequest.getPrice(), updateRequestCaptor.getValue().getPrice());
    }
}
