package com.microservices.ecommerce.basket.service.model.event;

import com.microservices.ecommerce.basket.service.config.TopicConfig;

public class UserMadeDeleteBasketOperation extends UserMadeBasketOperation{

    public UserMadeDeleteBasketOperation() {
        super(TopicConfig.getUserMadeDeleteBasketOperation());
    }
}
