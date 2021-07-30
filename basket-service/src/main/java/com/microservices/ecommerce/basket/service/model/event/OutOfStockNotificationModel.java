package com.microservices.ecommerce.basket.service.model.event;

import java.util.ArrayList;

public class OutOfStockNotificationModel {
    private long productId;
    private long sellerId;
    private String productName;
    private String sellerName;
    private ArrayList<Long> sellerIds = new ArrayList<>();
    private ArrayList<Long> sellerNames = new ArrayList<>();
    private ArrayList<Long> userIds = new ArrayList<>();

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public ArrayList<Long> getSellerIds() {
        return sellerIds;
    }

    public void setSellerIds(ArrayList<Long> sellerIds) {
        this.sellerIds = sellerIds;
    }

    public ArrayList<Long> getSellerNames() {
        return sellerNames;
    }

    public void setSellerNames(ArrayList<Long> sellerNames) {
        this.sellerNames = sellerNames;
    }

    public ArrayList<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<Long> userIds) {
        this.userIds = userIds;
    }
}
