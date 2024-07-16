package com.musinsa.product.api;

import com.musinsa.product.api.request.ProductCreateRequest;
import com.musinsa.product.api.request.ProductDeleteRequest;
import com.musinsa.product.api.request.ProductUpdateRequest;
import com.musinsa.product.facade.ProductInventoryFacade;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Builder
@RequiredArgsConstructor
public class ProductApi {

    private final ProductInventoryFacade productInventoryFacade;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
        productInventoryFacade.addProduct(productCreateRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Valid @RequestBody ProductDeleteRequest productDeleteRequest) {
        productInventoryFacade.deleteProduct(productDeleteRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
        productInventoryFacade.updateProduct(productUpdateRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
