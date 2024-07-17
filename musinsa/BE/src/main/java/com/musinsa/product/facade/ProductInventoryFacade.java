package com.musinsa.product.facade;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.common.service.CacheService;
import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductDeleteRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

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

    private final CacheService cacheService;

    /**
     * 브랜드 및 상품을 추가
     * */
    @CacheEvict(value = "productCache", allEntries = true)
    public void createProduct(ProductCreateRequest productCreateRequest){
        cacheService.evictAllSpecifiedCaches();
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
        } else {
            throw new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, category);
        }
    }


    /**
     * 브랜드 및 상품을 업데이트
     * */

    public void updateProduct(ProductUpdateRequest productUpdateRequest){
        cacheService.evictAllSpecifiedCaches();

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
        } else {
            throw new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, category);
        }
    }


    /**
     * 브랜드 및 상품을 삭제
     * */

    @CacheEvict(value = "productCache", allEntries = true)
    public void deleteProduct(ProductDeleteRequest productDeleteRequest){
        cacheService.evictAllSpecifiedCaches();

        String category = productDeleteRequest.getCategory();
        Long id = productDeleteRequest.getId();

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
        } else {
            throw new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, category);
        }
    }

}
