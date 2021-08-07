package com.microservices.ecommerce.basket.service.model.event;

import java.util.ArrayList;

public abstract class NotificationEvents {
    private ArrayList<Long> userIdList;
    private String message;
    private String topicName;
    private long productId;
    private String productTitle;

    public NotificationEvents(ArrayList<Long> userIdList, String message, String topicName, long productId, String productTitle) {
        this.userIdList = userIdList;
        this.message = message;
        this.topicName = topicName;
        this.productId = productId;
        this.productTitle = productTitle;
    }

    public ArrayList<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(ArrayList<Long> userIdList) {
        this.userIdList = userIdList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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

    public void addUser(Long id) {
        if(userIdList == null) {
            userIdList = new ArrayList<Long>();
        }
        userIdList.add(id);
    }
}
