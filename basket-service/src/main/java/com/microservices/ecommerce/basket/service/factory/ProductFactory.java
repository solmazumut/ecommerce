package com.microservices.ecommerce.basket.service.factory;

import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.routes.request.models.AddProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.SetProductQuantityRequestObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductFactory {

    public static Product createTestProduct() {
        Seller testSeller1 = SellerFactory.createTestSeller();
        Seller testSeller2 = SellerFactory.createTestSeller();
        testSeller2.setSellerId(2);
        Product product = new Product(1,"imageUrl","computer",
                new ArrayList<Seller>(Arrays.asList(testSeller1, testSeller2)));
        return product;
    }

    public static Product createProductWithOneSellerAndUser(long productId, String imageUrl, String title, long sellerId, int stock,
                                                           float price, long userId, int quantity) {
        Seller seller = SellerFactory.createSellerWithOneUser(sellerId, stock, price, userId, quantity);
        Product product = new Product(productId, imageUrl, title,
                new ArrayList<Seller>(Arrays.asList(seller)));
        return product;
    }

    public static Product createProduct(long id, String imageUrl, String title) {
        Product product = new Product(1, imageUrl, title,  new ArrayList<Seller>());
        return product;
    }

    public static Product createProductWithSeller(long id, String imageUrl, String title, Seller seller) {
        Product product = new Product(id, imageUrl, title, new ArrayList<Seller>(Arrays.asList(seller)));
        return product;
    }

    public static Product createProductWithSellers(long id, String imageUrl, String title, ArrayList<Seller> sellers) {
        Product product = new Product(id, imageUrl, title, sellers);
        return product;
    }


    public static Product createProductWithRequestObject(AddProductRequestObject addProductRequestObject) {
        long productId = addProductRequestObject.getProductId();
        String imageUrl = addProductRequestObject.getProductImageUrl();
        String title = addProductRequestObject.getProductTitle();
        long sellerId = addProductRequestObject.getSellerId();
        int stock = addProductRequestObject.getStock();
        float price = addProductRequestObject.getPrice();
        long userId = addProductRequestObject.getUserId();
        int quantity = 1;
        Product product = ProductFactory.createProductWithOneSellerAndUser(productId, imageUrl, title, sellerId, stock, price, userId, quantity);
        return product;
    }

    public static Product createProductWithRequestObject(SetProductQuantityRequestObject setProductQuantityRequestObject) {
        long productId = setProductQuantityRequestObject.getProductId();
        String imageUrl = setProductQuantityRequestObject.getProductImageUrl();
        String title = setProductQuantityRequestObject.getProductTitle();
        long sellerId = setProductQuantityRequestObject.getSellerId();
        int stock = setProductQuantityRequestObject.getStock();
        float price = setProductQuantityRequestObject.getPrice();
        long userId = setProductQuantityRequestObject.getUserId();
        int quantity = setProductQuantityRequestObject.getQuantity();
        Product product = ProductFactory.createProductWithOneSellerAndUser(productId, imageUrl, title, sellerId, stock, price, userId, quantity);
        return product;
    }
}
