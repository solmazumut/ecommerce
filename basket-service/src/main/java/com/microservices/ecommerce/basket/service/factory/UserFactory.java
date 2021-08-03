package com.microservices.ecommerce.basket.service.factory;

import com.microservices.ecommerce.basket.service.model.User;

public class UserFactory {

    public static User createTestUser() {
        User user = new User(1, 1);
        return user;
    }

    public static User createUser(long id) {
        User user = new User(id, 1);
        return user;
    }

    public static User createUserWithQuantity(long id, int quantity) {
        User user = new User(id, quantity);
        return user;
    }
}
