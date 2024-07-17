
`조회(search)`, `상품(product)` 개념을 기반으로 패키지를 나누었습니다.

```
com.musinsa
	ㄴproduct
         ㄴsearch
         ㄴcommon
         ㄴexception
```

search 에서는 `구현1~3` 까지 조회를 관리합니다.
product 에서는 `구현 4`의 상품을 담당합니다.
common은 전체 공통 기능을 담당합니다.
exception은 전 exception 처리를 담당합니다.
<br><br>
### **com.musinsa.product 패키지 설명**
주로 `구현 4`의 상품을 담당하며
하위 패키지 구조는 `api`,`facade`, `service`, `domain`,  `infrastructure`로 구성되어 있습니다.

```
...
  ㄴ product
	  ㄴ api
	     ㄴ request
	     ㄴ response
	  ㄴ facade
	  ㄴ service
	  ㄴ domain
	  ㄴ infrastructure
		 ㄴ entity
		 ㄴ jpa
	 
```
<br>
### **com.musinsa.product.api 패키지 설명**
api는 외부와의 연결을 담당합니다. api 패키지 내부에는 외부에 공개된 api, request, response 정보를 구분 할 수 있도록 패키지가 분리되어있습니다.
```
...
 ㄴ api
	 ㄴ request
	      ㄴ ProductCreateRequest.java
	      ㄴ ProductUpdateRequest.java
	      ㄴ ProductDeleteRequest.java
	 ㄴ response
		  ㄴ ProductResponse.java
  ㄴ ProductApi.java	 	 

```
<br>
Request.java 는 클라이언트의 `입력값을 검증`하고, 클라이언트에게 `실패 값`과 `실패 사유`를 알려줍니다.
```
@Builder  
@Getter  
public class ProductUpdateRequest {  
/**  
* 카테고리(모자,양말..)  
* */    @NotNull(message = "카테고리는 null 이어서는 안됩니다.")  
@NotEmpty(message = "카테고리 값은 빈 값이 될 수 없습니다. 값을 입력해주세요.")  
@NotBlank(message = "카테고리 값은 공백이 될 수 없습니다. 값을 입력해주세요.")  
String category;  
/**  
* 브랜드  
* */  
@NotNull(message = "브랜드 null 이어서는 안됩니다.")  
@NotEmpty(message = "브랜드 값은 빈 값이 될 수 없습니다. 값을 입력해주세요.")  
@NotBlank(message = "브랜드 값은 공백이 될 수 없습니다. 값을 입력해주세요.")  
String brand;

    /**  
     * 카테고리 id  
     * */    @NotNull(message = "상품 id는 null 이어서는 안됩니다.")  
    @Min(0)  
    Long id;  
  
    /**  
     * 가격  
     * */  
    @NotNull(message = "가격은 null 이어서는 안됩니다.")  
    @Min(0)  
    BigDecimal price;  
}
```
![image](https://github.com/user-attachments/assets/d9617c6e-5288-4922-bd64-5d46b9ae226a)

<br>
`ProductApi.java` 는 구현4의 api를 담당합니다.

```
@RestController  
@RequestMapping("/api/products")  
@Builder  
@RequiredArgsConstructor  
public class ProductApi {

    private final ProductInventoryFacade productInventoryFacade;  
  
    /**  
     * 브랜드 및 상품 추가 API  
     *     * */    @PostMapping  
    public ResponseEntity<Void> create(@Valid @RequestBody ProductCreateRequest productCreateRequest) {  
        productInventoryFacade.createProduct(productCreateRequest);  
        return ResponseEntity  
                .status(HttpStatus.CREATED)  
                .build();  
    }
    ...
    ...
}

```
<br>
### **com.musinsa.product.facade 패키지 설명**
facade 는 api 계층에서 온 요구사항을 전처리 하여 domain과 service 계층으로 전달하는 역할을 합니다.

```
...
ㄴ product
ㄴ facade
ㄴ ProductInventoryFacade.java
...
...

```

`ProductInventoryFacade.java` 에서는 구현1~3에 해당되는 조회 기능에서 사용되는 캐시를 초기화 합니다.
api의 요구사항에 맞게 domain과 service으로 분기처리 합니다.
```
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
        } 
        ...
        ...
    }
    ...
    ...
    {}
```

<br>
### **com.musinsa.product.service 패키지 설명**
카테고리 별로 서비스가 존재합니다.
domain 계층과 협업하여 비즈니스 로직을 담당하며, 로직 프로세스 실패시 클라이언트에게`실패 값`과 `실패 사유`를 만듭니다.
```
...
ㄴ service
ㄴ AccessoryService.java
ㄴ BagService.java
ㄴ HatService.java
...
...
```

**BagService.java**
```
@RequiredArgsConstructor  
@Service  
public class BagService {  
private final BagRepository bagRepository;

    public Bag getBagMinimumPrice(){  
        return bagRepository.findFirstByOrderByPriceAscBrandDesc()  
                .map(BagEntity::toModel)  
                .orElseThrow(() -> new CategoryException.CategoryItemNotFoundException(CATEGORY_ITEM_NOT_FOUND, "Accessory"));  
    }

	  @Transactional  
public void updateBag(Long id, Bag bag){  
BagEntity bagEntity = bagRepository.findById(id).get();  
bagEntity.change(bag);  
}
...
...
}
```

<br>

### **com.musinsa.product.domain 패키지 설명**
카테고리 별로 도메인이 존재합니다.
각 도메인은 자신의 상태를 변경하며 자신과 관련있는 비즈니스 코드와 협업하는 역할을 합니다.

```
...
ㄴ domain
ㄴ Accessory.java
ㄴ Bag.java
ㄴ Hat.java
...
...
```

**Bag.java**
```
@ToString  
@Builder  
@Getter  
public class Bag {  
public Long id;  
String brand;  
BigDecimal price;

    public SearchProduct toModel(){  
        return SearchProduct.builder()  
                .category("가방")  
                .brand(brand)  
                .price(price)  
                .build();  
    }  
  
    public static Bag create(ProductCreateRequest productCreateRequest) {  
        return Bag.builder()  
                .brand(productCreateRequest.getBrand())  
                .price(productCreateRequest.getPrice())  
                .build();  
    }  
  
    public static Bag update(ProductUpdateRequest productUpdateRequest) {  
        return Bag.builder()  
                .brand(productUpdateRequest.getBrand())  
                .price(productUpdateRequest.getPrice())  
                .build();  
    }  
}
```
<br>


### **com.musinsa.product.infrastructure 패키지 설명**
DB 인프라와 직접적으로 핸들링하는 역할을 합니다.
하위 `entity` 에는 db의 스키마 모델, `jpa` 에는 db에 접근하는 역할을 담당합니다.

```
...
ㄴ infrastructure
ㄴ entity
ㄴ AccessoryEntity.java
...
ㄴ jpa
ㄴ AccessoryRepository.java
...
```


<br>
<br>
### **com.musinsa.search 패키지 설명**
주로 `구현 1~3`의 기능을 담당합니다.

앞선  product 패키지의 하위 구조와 비슷하여 주요 기능만 설명합니다.
```
ㄴ search
ㄴ api
ㄴ response
searchApi.java
ㄴ facade
ㄴ PricingFacade.java
ㄴ domain
```


**SearchApi.java**
구현 1~3 의 api 입니다.

```
@RequiredArgsConstructor  
@RestController  
@ResponseBody  
public class SearchApi {

    private final PricingFacade pricingFacade;  
  
  
    /**  
     * 1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API  
     *     * */    @GetMapping("/api/cheapest-category")  
    public ResponseEntity<CategoryLowestPriceResponse> getLowestPriceCategoryProduct(){  
        return new ResponseEntity<>(pricingFacade.getMinimumProduct(),  
                HttpStatus.FOUND);  
    }  
  
    /**  
     *     * 2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을  
     * 조회하는 API  
     * */    @GetMapping("/api/cheapest-brand")  
    public ResponseEntity<BrandLowestPriceResponse> getLowestPriceBrandProduct(){  
        return new ResponseEntity<>(pricingFacade.getLowestBrandProduct(),  
                HttpStatus.FOUND);  
    }  
  
  
    /**  
     *  3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API  
     *     * */    @GetMapping("/api/cheapest-hightest/{category}")  
    public ResponseEntity<CategoryOneLowestPriceResponse> getLowestPriceBrandProduct(@PathVariable final String category){  
        return new ResponseEntity<>(pricingFacade.getLowHigtestBrandPrice(category),  
                HttpStatus.FOUND);  
    }  

}
```


**PricingFacade.java**

```
@RequiredArgsConstructor  
@Service  
public class PricingFacade {

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
    @Cacheable(value = "minimumProduct", key = "'minimumProduct'")  
    public CategoryLowestPriceResponse getMinimumProduct(){..}

	/**  
	* 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을  
	* 조회하는 API
	* 
	* * */
	@Cacheable(value = "lowestBrandProduct", key = "'lowestBrandProduct'")
	public BrandLowestPriceResponse getLowestBrandProduct(){..}

	/**  
	 * 카테고리 이름으로 최저가격 조회하기  
	 * @param category, (top, outer, bottom, sneakers, hat, socks, accessory)  
	 *
	 */
	@Cacheable(value = "productCacheByCategory", key = "#category")  
	public CategoryOneLowestPriceResponse getLowHigtestBrandPrice(String category){..}
```