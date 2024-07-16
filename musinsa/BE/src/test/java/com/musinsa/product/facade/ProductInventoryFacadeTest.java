package com.musinsa.product.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductDeleteRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.domain.Top;
import com.musinsa.product.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProductInventoryFacadeTest {

    @Mock
    private TopService topService;
    @Mock
    private OuterService outerService;
    @Mock
    private BottomService bottomService;
    @Mock
    private SneakersService sneakersService;
    @Mock
    private BagService bagService;
    @Mock
    private HatService hatService;
    @Mock
    private SocksService socksService;
    @Mock
    private AccessoryService accessoryService;

    @InjectMocks
    private ProductInventoryFacade productInventoryFacade;

    private ProductCreateRequest createRequest;
    private ProductUpdateRequest updateRequest;
    private ProductDeleteRequest deleteRequest;

    void setUp(String categorty) {
        if (categorty.equals(RequestUtils.TOP)){
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
        } else if(categorty.equals(RequestUtils.SOCKS)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.SOCKS)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.SOCKS)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.SOCKS)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.SNEAKERS)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.SNEAKERS)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.SNEAKERS)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.SNEAKERS)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.OUTER)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.OUTER)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.OUTER)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.OUTER)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.HAT)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.HAT)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.HAT)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.HAT)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.BOTTOM)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.BOTTOM)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.BOTTOM)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.BOTTOM)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.BAG)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.BAG)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.BAG)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.BAG)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.ACCESSORY)){
            createRequest = ProductCreateRequest.builder()
                    .category(RequestUtils.ACCESSORY)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .category(RequestUtils.ACCESSORY)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.ACCESSORY)
                    .id(1L)
                    .build();
        }

    }

    @Test
    public void 상의_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.TOP);
        //when
        productInventoryFacade.addProduct(createRequest);

        //then
        verify(topService, times(1)).createTop(any(Top.class));
    }

    @Test
    public void 상의_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.TOP);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(topService, times(1)).updateTop(any(Long.class), any(Top.class));
    }

    @Test
    public void 상의_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.TOP);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(topService, times(1)).deleteTop(deleteRequest.getId());
    }
}
