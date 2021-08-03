package com.microservices.ecommerce.basket.service.routes.request.models;

public class AddProductRequestObject {

    long productId;
    String productTitle;
    String productImageUrl;
    long sellerId;
    float price;
    int stock;
    long userId;

    public AddProductRequestObject() {
    }

    public AddProductRequestObject(long productId, String productTitle, String productImageUrl,
                                   long sellerId, float price, int stock, long userId) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImageUrl = productImageUrl;
        this.sellerId = sellerId;
        this.price = price;
        this.stock = stock;
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
