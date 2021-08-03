package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.factory.ProductFactory;
import com.microservices.ecommerce.basket.service.factory.SellerFactory;
import com.microservices.ecommerce.basket.service.factory.UserFactory;
import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.model.User;
import com.microservices.ecommerce.basket.service.repository.ProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @Test
    @Order(1)
    public void findProduct() throws InterruptedException {

        //Given
        Product product = ProductFactory.createTestProduct();
        String id = String.valueOf(product.getProductId());
        Thread.sleep(50);

        //When
        productService.create(product);
        Thread.sleep(50);
        Product foundProduct = productService.findById(id);

        //Then
        assertEquals(product.getProductId(), foundProduct.getProductId());

    }


    @Test
    @Order(2)
    public void addExistingUserToProduct() throws InterruptedException {

        //Given
        Product product = ProductFactory.createTestProduct();
        productRepository.deleteById(String.valueOf(product.getProductId()));
        String id = String.valueOf(product.getProductId());
        long sellerId = 10;
        Seller seller = SellerFactory.createSellerWithOneUser(sellerId,10,10,9,1);
        product.addSeller(seller);
        Thread.sleep(50);
        productService.create(product);
        Thread.sleep(50);

        //When
        long userId = 10;
        User user = UserFactory.createUser(userId);
        product.addOrUpdateUser(product.getSellers().get(0), user);
        productService.create(product);

        Product foundProduct = productService.findById(id);

        //Then
        Seller foundSeller = foundProduct.getSellerWithId(sellerId);
        assertTrue(foundSeller.isUserExist(userId));
        Thread.sleep(50);

    }





}
