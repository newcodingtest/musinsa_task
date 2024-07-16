package com.musinsa.product.api;

import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.api.response.ProductResponse;
import com.musinsa.product.facade.ProductCRUDFacade;
import com.musinsa.product.facade.ProductInventoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductCRUDFacade productCRUDFacade;

    private final ProductInventoryFacade productInventoryFacade;
    
    /**
     * 상품 조회 리스트 페이지
     * 
     * */
    @GetMapping("/products")
    public String list(Model model) {
        List<ProductResponse> products = productCRUDFacade.findItems()
                .stream()
                .map(ProductResponse::fromModel)
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "products/productList";
    }
    
    /**
     * 상품 상세 페이지
     * 
     * */

    @GetMapping("products/{category}/{productId}")
    public String updateItemForm(@PathVariable("category") String category,
                                 @PathVariable("productId") Long productId, Model model) {
        ProductResponse product = ProductResponse.fromModel(productCRUDFacade
                                                                 .findOne(category, productId));
        model.addAttribute("product", product);
        return "products/updateProductForm";
    }
    
    /**
     * 브랜드 및 상품 수정 API + 페이지
     * 
     * */

    @PutMapping("/products")
    public String updateItem(
                             @ModelAttribute("form") ProductUpdateRequest productUpdateRequest) {
        productInventoryFacade.updateProduct(productUpdateRequest);

        return "redirect:/products/productList";
    }
}
