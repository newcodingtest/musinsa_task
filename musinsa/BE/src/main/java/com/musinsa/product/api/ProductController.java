package com.musinsa.product.api;

import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.api.response.ProductResponse;
import com.musinsa.product.facade.ProductCRUDFacade;
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
    @GetMapping("/products")
    public String list(Model model) {
        List<ProductResponse> products = productCRUDFacade.findItems()
                .stream()
                .map(ProductResponse::fromModel)
                .collect(Collectors.toList());

        model.addAttribute("products", products);
        return "products/productList";
    }

    @GetMapping("products/{category}/{productId}")
    public String updateItemForm(@PathVariable("category") String category,
                                 @PathVariable("productId") Long productId, Model model) {
        ProductResponse product = ProductResponse.fromModel(productCRUDFacade
                                                                 .findOne(category, productId));
        model.addAttribute("product", product);
        return "products/updateProductForm";
    }

    @PutMapping("products/{category}/{productId}")
    public String updateItem(@PathVariable("category") String category,
                             @PathVariable("productId") Long productId,
                             @ModelAttribute("form") ProductUpdateRequest form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/products";
    }
}
