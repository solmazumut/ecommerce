package com.microservices.ecommerce.basket.service.model.event;


import com.microservices.ecommerce.basket.service.config.TopicConfig;

public class UserMadeAddBasketOperation extends UserMadeBasketOperation{

    String productTitle;
    String productImageUrl;
    float price;
    int stock;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}