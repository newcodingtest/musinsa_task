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

    @BeforeEach
    void setUp() {
        createRequest = ProductCreateRequest.builder()
                .category(RequestUtils.TOP)
                .build();

        updateRequest = ProductUpdateRequest.builder()
                .category(RequestUtils.TOP)
                .build();

        deleteRequest = ProductDeleteRequest.builder()
                .category(RequestUtils.TOP)
                .id(1L)
                .build();
    }

    @Test
    public void testAddProduct() {
        // When
        productInventoryFacade.addProduct(createRequest);

        // Then
        verify(topService, times(1)).createTop(any(Top.class));
    }

    @Test
    public void testUpdateProduct() {
        // When
        productInventoryFacade.updateProduct(updateRequest);

        // Then
        verify(topService, times(1)).updateTop(any(Top.class));
    }

    @Test
    public void testDeleteProduct() {
        // When
        productInventoryFacade.deleteProduct(deleteRequest);

        // Then
        verify(topService, times(1)).deleteTop(deleteRequest.getId());
    }

    @Test
    public void testAddProductForAccessory() {
        // Given
        createRequest.setCategory(RequestUtils.ACCESSORY);

        // When
        productInventoryFacade.addProduct(createRequest);

        // Then
        verify(accessoryService, times(1)).createAccssory(any(Accessory.class));
    }

    @Test
    public void testUpdateProductForAccessory() {
        // Given
        updateRequest.setCategory(RequestUtils.ACCESSORY);

        // When
        productInventoryFacade.updateProduct(updateRequest);

        // Then
        verify(accessoryService, times(1)).updateAccssory(any(Accessory.class));
    }

    @Test
    public void testDeleteProductForAccessory() {
        // Given
        deleteRequest.setCategory(RequestUtils.ACCESSORY);

        // When
        productInventoryFacade.deleteProduct(deleteRequest);

        // Then
        verify(accessoryService, times(1)).deleteAccssory(deleteRequest.getId());
    }
}
