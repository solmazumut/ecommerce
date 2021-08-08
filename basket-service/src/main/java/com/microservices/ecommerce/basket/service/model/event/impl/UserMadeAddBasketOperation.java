package com.microservices.ecommerce.basket.service.model.event.impl;


import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.UserMadeBasketOperation;

public class UserMadeAddBasketOperation extends UserMadeBasketOperation {

    String productTitle;
    String productImageUrl;
    float price;

    public UserMadeAddBasketOperation() {
        super(TopicConfig.getUserMadeAddBasketOperation());
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}