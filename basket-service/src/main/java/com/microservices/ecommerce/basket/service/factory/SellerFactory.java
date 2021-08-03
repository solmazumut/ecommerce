package com.microservices.ecommerce.basket.service.factory;

import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SellerFactory {

    public static Seller createTestSeller() {
            User testUser1 = UserFactory.createTestUser();
        User testUser2 = UserFactory.createTestUser();
        testUser2.setUserId(2);
        Seller seller = new Seller(1,5,10, new ArrayList<User>(Arrays.asList(testUser1, testUser2)));
        return seller;
    }

    public static Seller createSellerWithOneUser(long sellerId, int stock, float price, long userId, int quantity) {
        User user = UserFactory.createUserWithQuantity(userId, quantity);
        Seller seller = new Seller(sellerId, stock, price, new ArrayList<User>(Arrays.asList(user)));
        return seller;
    }

    public static Seller createSeller(long id, int stock, float price) {
        Seller seller = new Seller(id, stock, price, new ArrayList<User>());
        return seller;
    }

    public static Seller createSellerWithUser(long id, int stock, float price, User user) {
        Seller seller = new Seller(id, stock, price,  new ArrayList<User>(Arrays.asList(user)));
        return seller;
    }

    public static Seller createSellerWithUserList(long id, int stock, float price, ArrayList<User> users) {
        Seller seller = new Seller(id, stock, price, users);
        return seller;
    }


}
