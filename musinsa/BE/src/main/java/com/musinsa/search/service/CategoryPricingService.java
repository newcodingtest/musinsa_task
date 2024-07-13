package com.musinsa.search.service;

import com.musinsa.search.api.response.SearchResponse;
import com.musinsa.search.domain.Product;
import com.musinsa.shop.domain.*;
import com.musinsa.shop.infrastructure.jpa.*;
import com.musinsa.shop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryPricingService {

    private final AccessoryService accessoryService;
    private final BagService bagService;
    private final BottomService bottomService;
    private final HatService hatService;
    private final OuterService outerService;
    private final SneakersService sneakersService;
    private final SocksService socksService;
    private final TopService topService;


    public SearchResponse getMinimumProduct(){
        BigDecimal totalPrice = BigDecimal.ZERO;

        Top top = topService.getAccessoryMinimumPrice();
        Outer outer = outerService.getAccessoryMinimumPrice();
        Bottom bottom = bottomService.getAccessoryMinimumPrice();
        Sneakers sneakers = sneakersService.getAccessoryMinimumPrice();
        Bag bag = bagService.getAccessoryMinimumPrice();
        Hat hat = hatService.getAccessoryMinimumPrice();
        Socks socks = socksService.getAccessoryMinimumPrice();
        Accessory accessory = accessoryService.getAccessoryMinimumPrice();

        List<Product> products = Arrays.asList(
                top.toModel(),
                outer.toModel(),
                bottom.toModel(),
                sneakers.toModel(),
                bag.toModel(),
                hat.toModel(),
                socks.toModel(),
                accessory.toModel()
        );



        totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return SearchResponse.builder()
                .products(products)
                .totalPrice(totalPrice)
                .build();
    }

}
