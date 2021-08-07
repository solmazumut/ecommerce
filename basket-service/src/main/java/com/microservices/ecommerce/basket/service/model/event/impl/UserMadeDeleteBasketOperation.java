package com.microservices.ecommerce.basket.service.model.event.impl;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.UserMadeBasketOperation;

public class UserMadeDeleteBasketOperation extends UserMadeBasketOperation {

    public UserMadeDeleteBasketOperation() {
        super(TopicConfig.getUserMadeDeleteBasketOperation());
    }
}
