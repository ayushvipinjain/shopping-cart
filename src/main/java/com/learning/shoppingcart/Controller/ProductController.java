package com.learning.shoppingcart.Controller;

import com.learning.shoppingcart.Entity.ProductDetails;
import com.learning.shoppingcart.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product")
    public ResponseEntity<ProductDetails> saveProduct(@RequestBody ProductDetails productDetails) {
        System.out.println(productDetails);
        return ResponseEntity.ok(productService.saveProduct(productDetails));
    }

    @GetMapping("products")
    public ResponseEntity<List<ProductDetails>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDetails> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
