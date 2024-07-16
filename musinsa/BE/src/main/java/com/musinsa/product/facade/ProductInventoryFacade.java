package com.musinsa.product.facade;

import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductInventoryFacade {
    private final TopService topService;
    private final OuterService outerService;
    private final BottomService bottomService;
    private final SneakersService sneakersService;
    private final BagService bagService;
    private final HatService hatService;
    private final SocksService socksService;
    private final AccessoryService accessoryService;

    /**
     * 브랜드 및 상품을 추가
     * */
    //@CacheEvict(value = "productCache", key = "'lowestBrandProduct'")
    public void createProduct(ProductCreateRequest productCreateRequest){
        addProductExcute(productCreateRequest);
    }

    private void addProductExcute(ProductCreateRequest productCreateRequest) {
        String category = productCreateRequest.getCategory();
        if(category.equals(RequestUtils.TOP)){
            topService.createTop(Top
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.OUTER)){
            outerService.createOuter(Outer
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.BOTTOM)){
            bottomService.createBottom(Bottom
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.SNEAKERS)){
            sneakersService.createSneakers(Sneakers
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.BAG)){
            bagService.createBag(Bag
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.HAT)){
            hatService.createHat(Hat
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.SOCKS)){
            socksService.createSocks(Socks
                    .create(productCreateRequest));
        } else if(category.equals(RequestUtils.ACCESSORY)){
            accessoryService.createAccssory(Accessory
                    .create(productCreateRequest));
        }
    }

    /**
     * 브랜드 및 상품을 업데이트
     * */
<<<<<<< HEAD
    @Transactional
    @Cacheable(value = "productCache", key = "'category'")
    public void updateProduct(ProductUpdateRequest productUpdateRequest){
=======
    //@Cacheable(value = "productCache", key = "'category'")
    public void updateProduct(ProductUpdateRequest productUpdateRequest){
        String category = productUpdateRequest.getCategory();
        Long id = productUpdateRequest.getId();
        if(category.equals(RequestUtils.TOP)){
            topService.updateTop(id, Top.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.OUTER)){
            outerService.updateOuter(id, Outer.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.BOTTOM)){
            bottomService.updateBottom(id, Bottom.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.SNEAKERS)){
            sneakersService.updateSneakers(id, Sneakers.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.BAG)){
            bagService.updateBag(id, Bag.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.HAT)){
            hatService.updateHat(id, Hat.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.SOCKS)){
            socksService.updateSocks(id, Socks.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.ACCESSORY)){
            accessoryService.updateAccssory(id, Accessory.update(productUpdateRequest));
        }
    }

    private void updateProductExcute(ProductUpdateRequest productUpdateRequest) {
>>>>>>> parent of 44138af (FIX: 기능 오류 점검. bag -> bottom)
        String category = productUpdateRequest.getCategory();
        Long id = productUpdateRequest.getId();

        if(category.equals(RequestUtils.TOP)){
            topService.updateTop(id, Top.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.OUTER)){
            outerService.updateOuter(id, Outer.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.BOTTOM)){
            bottomService.updateBottom(id, Bottom.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.SNEAKERS)){
            sneakersService.updateSneakers(id, Sneakers.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.BAG)){
            bagService.updateBag(id, Bag.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.HAT)){
            hatService.updateHat(id, Hat.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.SOCKS)){
            socksService.updateSocks(id, Socks.update(productUpdateRequest));
        } else if(category.equals(RequestUtils.ACCESSORY)){
            accessoryService.updateAccssory(id, Accessory.update(productUpdateRequest));
        }
    }

    //@CacheEvict(value = "productCache", allEntries = true)


    /**
     * 브랜드 및 상품을 삭제
     * */
<<<<<<< HEAD

    @CacheEvict(value = "productCache", allEntries = true)
    public void deleteProduct(String category, Long id){
=======
    // @CacheEvict(value = "productCache", allEntries = true)
    //@CacheEvict(value = "productCache", key = "'category'")
    public void deleteProduct(ProductDeleteRequest productDeleteRequest){
        deleteProductExcute(productDeleteRequest);
    }

    private void deleteProductExcute(ProductDeleteRequest productDeleteRequest) {
        String category = productDeleteRequest.getCategory();
        Long deleteId = productDeleteRequest.getId();
>>>>>>> parent of 44138af (FIX: 기능 오류 점검. bag -> bottom)
        if(category.equals(RequestUtils.TOP)){
            topService.deleteTop(id);
        } else if(category.equals(RequestUtils.OUTER)){
            outerService.deleteOuter(id);
        } else if(category.equals(RequestUtils.BOTTOM)){
            bottomService.deleteBottom(id);
        } else if(category.equals(RequestUtils.SNEAKERS)){
            sneakersService.deleteSneakers(id);
        } else if(category.equals(RequestUtils.BAG)){
            bagService.deleteBag(id);
        } else if(category.equals(RequestUtils.HAT)){
            hatService.deleteHat(id);
        } else if(category.equals(RequestUtils.SOCKS)){
            socksService.deleteSocks(id);
        } else if(category.equals(RequestUtils.ACCESSORY)){
            accessoryService.deleteAccssory(id);
        }
    }

}
