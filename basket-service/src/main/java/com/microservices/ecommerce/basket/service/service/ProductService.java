package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow( ()-> new RuntimeException(
                String.format("Product not found")
        ));
    }
}
