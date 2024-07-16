package com.musinsa.product.facade;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductDeleteRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class SearchProductInventoryFacadeTest {

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
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.SOCKS)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.SOCKS)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.SOCKS)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.SNEAKERS)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.SNEAKERS)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.SNEAKERS)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.SNEAKERS)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.OUTER)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.OUTER)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.OUTER)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.OUTER)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.HAT)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.HAT)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.HAT)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.HAT)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.BOTTOM)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.BOTTOM)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.BOTTOM)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.BOTTOM)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.BAG)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.BAG)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.BAG)
                    .build();

            deleteRequest = ProductDeleteRequest.builder()
                    .category(RequestUtils.BAG)
                    .id(1L)
                    .build();
        } else if(categorty.equals(RequestUtils.ACCESSORY)){
            createRequest = ProductCreateRequest.builder()
                    .brand("A")
                    .price(new BigDecimal(10000))
                    .category(RequestUtils.ACCESSORY)
                    .build();

            updateRequest = ProductUpdateRequest.builder()
                    .id(1L)
                    .brand("A")
                    .price(new BigDecimal(10000))
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
        productInventoryFacade.createProduct(createRequest);

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

    @Test
    public void 아우터_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.OUTER);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(outerService, times(1)).createOuter(any(Outer.class));
    }

    @Test
    public void 아우터_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.OUTER);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(outerService, times(1)).updateOuter(any(Long.class), any(Outer.class));
    }

    @Test
    public void 아우터_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.OUTER);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(outerService, times(1)).deleteOuter(deleteRequest.getId());
    }

    @Test
    public void 바지_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(bottomService, times(1)).createBottom(any(Bottom.class));
    }

    @Test
    public void 바지_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(bottomService, times(1)).updateBottom(any(Long.class), any(Bottom.class));
    }

    @Test
    public void 바지_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(bottomService, times(1)).deleteBottom(deleteRequest.getId());
    }

    @Test
    public void 모자_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.HAT);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(hatService, times(1)).createHat(any(Hat.class));
    }

    @Test
    public void 모자_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.HAT);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(hatService, times(1)).updateHat(any(Long.class), any(Hat.class));
    }

    @Test
    public void 모자_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.HAT);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(hatService, times(1)).deleteHat(deleteRequest.getId());
    }

    @Test
    public void 양말_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.SOCKS);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(socksService, times(1)).createSocks(any(Socks.class));
    }

    @Test
    public void 양말_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.SOCKS);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(socksService, times(1)).updateSocks(any(Long.class), any(Socks.class));
    }

    @Test
    public void 양말_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.SOCKS);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(socksService, times(1)).deleteSocks(deleteRequest.getId());
    }

    @Test
    public void 스니커즈_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.SNEAKERS);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(sneakersService, times(1)).createSneakers(any(Sneakers.class));
    }

    @Test
    public void 스니커즈_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.SNEAKERS);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(sneakersService, times(1)).updateSneakers(any(Long.class), any(Sneakers.class));
    }

    @Test
    public void 스니커즈_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.SNEAKERS);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(sneakersService, times(1)).deleteSneakers(deleteRequest.getId());
    }

    @Test
    public void 가방_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.BAG);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(bagService, times(1)).createBag(any(Bag.class));
    }

    @Test
    public void 가방_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.BAG);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(bagService, times(1)).updateBag(any(Long.class), any(Bag.class));
    }

    @Test
    public void 가방_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.BAG);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(bagService, times(1)).deleteBag(deleteRequest.getId());
    }

    @Test
    public void 하의_카테고리_상품을_추가_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.createProduct(createRequest);

        //then
        verify(bottomService, times(1)).createBottom(any(Bottom.class));
    }

    @Test
    public void 하의_카테고리_상품을_수정_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.updateProduct(updateRequest);

        //then
        verify(bottomService, times(1)).updateBottom(any(Long.class), any(Bottom.class));
    }

    @Test
    public void 하의_카테고리_상품을_삭제_할_수_있다() {
        //given
        setUp(RequestUtils.BOTTOM);
        //when
        productInventoryFacade.deleteProduct(deleteRequest);

        //then
        verify(bottomService, times(1)).deleteBottom(deleteRequest.getId());
    }
}
