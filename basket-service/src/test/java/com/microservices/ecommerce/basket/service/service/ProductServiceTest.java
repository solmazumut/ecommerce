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

import static org.junit.jupiter.api.Assertions.*;

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

        //When
        productService.create(product);
        Thread.sleep(50);
        Product foundProduct = productService.findById(id);

        //Then
        assertEquals(product.getProductId(), foundProduct.getProductId());
        Thread.sleep(50);
        productRepository.deleteById(id);
        Thread.sleep(50);
    }


    //ensure that products should be unique
    @Test
    @Order(2)
    public void addExistingUserToProduct() throws InterruptedException {

        //Given
        Product product = ProductFactory.createTestProduct();
        String id = String.valueOf(product.getProductId());
        long sellerId = 10;
        Seller seller = SellerFactory.createSellerWithOneUser(sellerId,10,10,9,1);
        product.addSeller(seller);
        productService.create(product);
        Thread.sleep(50);

        //When
        long userId = 10;
        User user = UserFactory.createUser(userId);
        product.addOrUpdateUser(seller, user);
        productService.createOrAddPropertyToExistProduct(product);

        Product foundProduct = productService.findById(id);

        //Then
        Seller foundSeller = foundProduct.getSellerWithId(sellerId);
        assertTrue(foundSeller.isUserExist(userId));
        Thread.sleep(50);
        productRepository.deleteById(id);
        Thread.sleep(50);
    }


    @Test
    @Order(3)
    public void deleteUser() throws InterruptedException {

        //Given
        Product product = ProductFactory.createTestProduct();
        long productId = product.getProductId();
        String stringProductId = String.valueOf(productId);
        long sellerId = 10;
        long userId = 9;
        Seller seller1 = SellerFactory.createSellerWithOneUser(sellerId,10,10,userId,1);
        Seller seller2 = SellerFactory.createSellerWithOneUser(sellerId,10,10,10,1);
        product.addSeller(seller1);
        product.addSeller(seller2);
        productService.create(product);
        Thread.sleep(50);

        //When
        productService.deleteUser(productId, sellerId, userId);
        Thread.sleep(50);

        //Then
        Product foundProduct = productService.findById(stringProductId);
        Seller foundSeller = foundProduct.getSellerWithId(sellerId);
        assertFalse(foundSeller.isUserExist(userId));
        Thread.sleep(50);
        productRepository.deleteById(stringProductId);
        Thread.sleep(50);
    }

    @Test
    @Order(4)
    public void automaticallyDeleteSellerWhenNoUsersLeft() throws InterruptedException {

        //Given
        Product product = ProductFactory.createTestProduct();
        long productId = product.getProductId();
        String stringProductId = String.valueOf(productId);
        long sellerId = 10;
        long userId = 9;
        Seller seller = SellerFactory.createSellerWithOneUser(sellerId,10,10,userId,1);
        product.addSeller(seller);
        Thread.sleep(50);
        productService.create(product);
        Thread.sleep(50);

        //When
        productService.deleteUser(productId, sellerId, userId);
        Thread.sleep(50);

        //Then
        Product foundProduct = productService.findById(stringProductId);

        assertEquals(null, foundProduct.getSellerWithId(sellerId));
        Thread.sleep(50);
        productRepository.deleteById(stringProductId);
        Thread.sleep(50);
    }



}
