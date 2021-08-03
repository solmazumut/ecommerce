package com.microservices.ecommerce.basket.service.factory;

import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;

import java.util.List;

public class ProductFactory {

    public static Product createTestProduct() {
        Seller testSeller1 = SellerFactory.createTestSeller();
        Seller testSeller2 = SellerFactory.createTestSeller();
        testSeller2.setSellerId(2);
        Product product = new Product(1,"imageUrl","computer", List.of(testSeller1, testSeller2));
        return product;
    }

    public static Product createProductWithOneSellerAndUser(long productId, String imageUrl, String title, long sellerId, int stock,
                                                           float price, long userId, int quantity) {
        Seller seller = SellerFactory.createSellerWithOneUser(sellerId, stock, price, userId, quantity);
        Product product = new Product(productId, imageUrl, title, List.of(seller));
        return product;
    }

    public static Product createProduct(long id, String imageUrl, String title) {
        Product product = new Product(1, imageUrl, title, List.of());
        return product;
    }

    public static Product createProductWithSeller(long id, String imageUrl, String title, Seller seller) {
        Product product = new Product(id, imageUrl, title, List.of(seller));
        return product;
    }

    public static Product createProductWithSellers(long id, String imageUrl, String title, List<Seller> sellers) {
        Product product = new Product(id, imageUrl, title, sellers);
        return product;
    }

    public static Product addSellerToProduct(Product product, Seller seller) {
        List<Seller> sellers = product.getSellers();
        if(sellers == null) {
            sellers = List.of(seller);
        } else {
            sellers.add(seller);
        }
        product.setSellers(sellers);
        return product;
    }
}
