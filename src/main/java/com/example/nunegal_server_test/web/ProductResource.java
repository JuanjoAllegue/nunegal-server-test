package com.example.nunegal_server_test.web;

import com.example.nunegal_server_test.model.service.ProductService;
import com.example.nunegal_server_test.model.service.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProductResource.PRODUCT_RESOURCE_URL)
public class ProductResource {
    public static final String PRODUCT_RESOURCE_URL = "/product";
    private static final Logger logger = LoggerFactory.getLogger(ProductResource.class);

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<ProductDTO>> getSimilarProducts(@PathVariable String productId) {
        List<ProductDTO> response = productService.getSimilarProducts(productId);
        return ResponseEntity.ok(response);
    }
}
