package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.model.User;
import com.microservices.ecommerce.basket.service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    private Product addPropertyToExistProduct(Product oldProduct, Product newProduct) {
        List<Seller> newSellers = newProduct.getSellers();
        for (Seller newSeller : newSellers) {
            oldProduct.addSeller(newSeller);
        }
        return productRepository.save(oldProduct);
    }

    private Product setPropertyToExistProduct(Product oldProduct, Product newProduct) {
        List<Seller> newSellers = newProduct.getSellers();
        for (Seller newSeller : newSellers) {
            oldProduct.addAndSetSeller(newSeller);
        }
        return productRepository.save(oldProduct);
    }

    public Product createOrAddPropertyToExistProduct(Product product) {
        Product finalProduct;
        try {
            Product oldProduct = this.findById(String.valueOf(product.getProductId()));
            finalProduct = addPropertyToExistProduct(oldProduct, product);
        } catch (Exception e) {
            finalProduct = create(product);
        }

        return finalProduct;
    }

    public Product createOrSetPropertyToExistProduct(Product product) {
        Product finalProduct;
        try {
            Product oldProduct = this.findById(String.valueOf(product.getProductId()));
            finalProduct = setPropertyToExistProduct(oldProduct, product);
        } catch (Exception e) {
            finalProduct = create(product);
        }

        return finalProduct;
    }



    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow( ()-> new RuntimeException(
                String.format("Product not found")
        ));
    }


    public void deleteUser(long productId, long sellerId, long userId) {
        Product product = this.findById(String.valueOf(productId));
        product.deleteUserFromSeller(sellerId, userId);
        boolean isThereAnyUserAddedProduct = product.isAnyUserAddedProduct();
        if (isThereAnyUserAddedProduct) {
            productRepository.save(product);
        } else {
            String id = String.valueOf(productId);
            productRepository.deleteById(id);
        }

    }
}
