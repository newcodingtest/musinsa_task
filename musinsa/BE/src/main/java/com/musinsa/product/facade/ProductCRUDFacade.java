package com.musinsa.product.facade;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.api.response.ProductResponse;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_ITEM_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ProductCRUDFacade {

    private final TopService topService;
    private final OuterService outerService;
    private final BottomService bottomService;
    private final SneakersService sneakersService;
    private final BagService bagService;
    private final HatService hatService;
    private final SocksService socksService;
    private final AccessoryService accessoryService;

    public List<Product> findItems(){
        return Stream.of(
                        topService.getAll().stream().map(Product::toModel),
                        outerService.getAll().stream().map(Product::toModel),
                        bottomService.getAll().stream().map(Product::toModel),
                        sneakersService.getAll().stream().map(Product::toModel),
                        bagService.getAll().stream().map(Product::toModel),
                        hatService.getAll().stream().map(Product::toModel),
                        socksService.getAll().stream().map(Product::toModel),
                        accessoryService.getAll().stream().map(Product::toModel)
                ).flatMap(s -> s)
                .collect(Collectors.toList());
    }

    public Product findOne(String category, Long id) {
        if(category.equals(RequestUtils.TOP)){
            return Product.toModel(topService.findOne(id));
        } else if(category.equals(RequestUtils.OUTER)){
            return Product.toModel(outerService.findOne(id));
        } else if(category.equals(RequestUtils.BOTTOM)){
            return Product.toModel(bottomService.findOne(id));
        } else if(category.equals(RequestUtils.SNEAKERS)){
            return Product.toModel(sneakersService.findOne(id));
        } else if(category.equals(RequestUtils.BAG)){
            return Product.toModel(bagService.findOne(id));
        } else if(category.equals(RequestUtils.HAT)){
            return Product.toModel(hatService.findOne(id));
        } else if(category.equals(RequestUtils.SOCKS)){
            return Product.toModel(socksService.findOne(id));
        } else if(category.equals(RequestUtils.ACCESSORY)){
            return Product.toModel(accessoryService.findOne(id));
        }
        throw new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, category);
    }
}
