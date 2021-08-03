package com.microservices.ecommerce.basket.service.routes.request.models;

public class RemoveProductRequestObject {

    long productId;
    long sellerId;
    long userId;

    public RemoveProductRequestObject() {
    }

    public RemoveProductRequestObject(long productId, long sellerId, long userId) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
