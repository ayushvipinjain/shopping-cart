package com.learning.shoppingcart.Service;

import com.learning.shoppingcart.Entity.ProductDetails;
import com.learning.shoppingcart.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDetails> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDetails getProductById(Long id) {
        if(productRepository.findById(id).isPresent()){
            return productRepository.findById(id).get();
        }
        else{
            return null;
        }
    }

//    public ProductDetails saveProduct(ProductDetails product) {
//        return productRepository.save(product);
//    }

    public ProductDetails saveProduct(ProductDetails productDetails) {
        return productRepository.save(productDetails);
    }
}
