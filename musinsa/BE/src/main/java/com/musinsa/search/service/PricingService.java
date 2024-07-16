package com.musinsa.search.service;

import com.musinsa.common.exception.CategoryException;
import com.musinsa.search.api.response.BrandLowestPriceResponse;
import com.musinsa.search.api.response.CategoryLowestPriceResponse;
import com.musinsa.search.api.response.CategoryOneLowestPriceResponse;
import com.musinsa.search.domain.SearchProduct;
import com.musinsa.common.utils.BigDecimalUtils;
import com.musinsa.common.utils.RequestUtils;
import com.musinsa.product.domain.*;
import com.musinsa.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.musinsa.common.exception.CategoryErrorCode.CATEGORY_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PricingService {

    private final AccessoryService accessoryService;
    private final BagService bagService;
    private final BottomService bottomService;
    private final HatService hatService;
    private final OuterService outerService;
    private final SneakersService sneakersService;
    private final SocksService socksService;
    private final TopService topService;


    /**
     * 카테고리 별로 최저가격 브랜드를 만들어준다.
     *
     * */
    @Cacheable(value = "productCache", key = "'minimumProduct'")
    public CategoryLowestPriceResponse getMinimumProduct(){
        BigDecimal totalPrice = BigDecimal.ZERO;

        Hat hat = hatService.getHatMinimumPrice();
        Top top = topService.getTopMinimumPrice();
        Outer outer = outerService.getOuterMinimumPrice();
        Bottom bottom = bottomService.getBottomMinimumPrice();
        Sneakers sneakers = sneakersService.getSneakersMinimumPrice();
        Bag bag = bagService.getBagMinimumPrice();
        Socks socks = socksService.getSocksMinimumPrice();
        Accessory accessory = accessoryService.getAccessoryMinimumPrice();

        List<SearchProduct> searchProducts = Arrays.asList(
                hat.toModel(),
                top.toModel(),
                outer.toModel(),
                bottom.toModel(),
                sneakers.toModel(),
                bag.toModel(),
                socks.toModel(),
                accessory.toModel()
        );

        totalPrice = searchProducts.stream()
                .map(SearchProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CategoryLowestPriceResponse.builder()
                .products(CategoryLowestPriceResponse.fromModel(searchProducts))
                .totalPrice(BigDecimalUtils.formatWithCommas(totalPrice))
                .build();
    }

    /**
     * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을
     * 조회하는 API
     *
     * */
    @Cacheable(value = "productCache", key = "'lowestBrandProduct'")
    public BrandLowestPriceResponse getLowestBrandProduct(){
        //브랜드 별 최저가 상품 조회
        List<SearchProduct> lowestSearchProductA = getLowestProductsByBrand("A");
        List<SearchProduct> lowestSearchProductB = getLowestProductsByBrand("B");
        List<SearchProduct> lowestSearchProductC = getLowestProductsByBrand("C");
        List<SearchProduct> lowestSearchProductD = getLowestProductsByBrand("D");
        List<SearchProduct> lowestSearchProductE = getLowestProductsByBrand("E");
        List<SearchProduct> lowestSearchProductF = getLowestProductsByBrand("F");
        List<SearchProduct> lowestSearchProductG = getLowestProductsByBrand("G");
        List<SearchProduct> lowestSearchProductH = getLowestProductsByBrand("H");
        List<SearchProduct> lowestSearchProductI = getLowestProductsByBrand("I");

        List<List<SearchProduct>> allProductLists = Arrays.asList(
                lowestSearchProductA,
                lowestSearchProductB,
                lowestSearchProductC,
                lowestSearchProductD,
                lowestSearchProductE,
                lowestSearchProductF,
                lowestSearchProductG,
                lowestSearchProductH,
                lowestSearchProductI
        );

        //최저가 브랜드들 중에서 가장 최저가인 브랜드 상품 조회
        List<SearchProduct> cheapestSearchProducts = allProductLists.stream()
                .min(Comparator.comparing(this::calculateTotalPrice))
                .orElseThrow(() -> new RuntimeException("No products found"));

        List<BrandLowestPriceResponse.CategoryPrice> categoryPrices = cheapestSearchProducts.stream()
                .map(BrandLowestPriceResponse.CategoryPrice::fromModel)
                .collect(Collectors.toList());

        //최저가
        BrandLowestPriceResponse.LowestProduct lowestProduct = BrandLowestPriceResponse.LowestProduct
                .builder()
                .brand(cheapestSearchProducts.get(0).getBrand())
                .categoryPrices(categoryPrices)
                .build();

        //총액
        BigDecimal totalPrice = calculateTotalPrice(cheapestSearchProducts);

        return BrandLowestPriceResponse.builder()
                .lowestProducts(lowestProduct)
                .totalPrice(BigDecimalUtils.formatWithCommas(totalPrice))
                .build();
    }


    /**
     * 카테고리 이름으로 최저가격 조회하기
     * @param category, (top, outer, bottom, sneakers, hat, socks, accessory)
     * */
    @Cacheable(value = "productCache", key = "'category'")
    public CategoryOneLowestPriceResponse getLowHigtestBrandPrice(String category){
        SearchProduct minimum = null;
        SearchProduct maximum = null;

        if (category.equals(RequestUtils.TOP)){
            minimum = topService.getTopMinimumPrice()
                    .toModel();
            maximum = topService.getTopMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.OUTER)){
            minimum = outerService.getOuterMinimumPrice()
                    .toModel();
            maximum = outerService.getOuterMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.BOTTOM)){
            minimum = bottomService.getBottomMinimumPrice()
                    .toModel();
            maximum = bottomService.getBottomMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.SNEAKERS)){
            minimum = sneakersService.getSneakersMinimumPrice()
                    .toModel();
            maximum = sneakersService.getSneakersMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.BAG)){
            minimum = bottomService.getBottomMinimumPrice()
                    .toModel();
            maximum = bottomService.getBottomMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.HAT)){
            minimum = hatService.getHatMinimumPrice()
                    .toModel();
            maximum = hatService.getHatMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.SOCKS)){
            minimum = socksService.getSocksMinimumPrice()
                    .toModel();
            maximum = socksService.getSocksMaximumPrice()
                    .toModel();
        }

        else if (category.equals(RequestUtils.ACCESSORY)){
            minimum = accessoryService.getAccessoryMaximumPrice()
                    .toModel();
            maximum = accessoryService.getAccessoryMaximumPrice()
                    .toModel();
        } else {
            throw new CategoryException.CategoryNotFoundException(CATEGORY_NOT_FOUND, category);
        }
        return CategoryOneLowestPriceResponse.builder()
                .category(minimum.getCategory())
                .lowest(CategoryOneLowestPriceResponse
                            .fromModel(minimum))
                .highest(CategoryOneLowestPriceResponse
                        .fromModel(maximum))
                .build();
    }

    /**
     * 브랜드 별 최저가격 상품 조회
     *
     * */
    private List<SearchProduct> getLowestProductsByBrand(String brand){
        BigDecimal totalPrice = BigDecimal.ZERO;

        Hat hat = hatService.getHatMinimumPriceByBrand(brand);
        Top top = topService.getTopMinimumPriceByBrand(brand);
        Outer outer = outerService.getOuterMinimumPriceByBrand(brand);
        Bottom bottom = bottomService.getBottomMinimumPriceByBrand(brand);
        Socks socks = socksService.getSocksMinimumPriceByBrand(brand);
        Sneakers sneakers = sneakersService.getSneakersMinimumPriceByBrand(brand);
        Accessory accessory = accessoryService.getAccessoryMinimumPriceByBrand(brand);
        Bag bag = bagService.getBagMinimumPriceByBrand(brand);

        List<SearchProduct> searchProducts = Arrays.asList(
                hat.toModel(),
                top.toModel(),
                outer.toModel(),
                bottom.toModel(),
                socks.toModel(),
                sneakers.toModel(),
                accessory.toModel(),
                bag.toModel()
        );
        return searchProducts;
    }


    /**
     * 주어진 카테고리의 총액 구하기
     * */
    private BigDecimal calculateTotalPrice(List<SearchProduct> searchProducts) {
        BigDecimal totalPrice =  searchProducts.stream()
                .map(SearchProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice;
    }

}
