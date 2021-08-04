package com.microservices.ecommerce.basket.service.model.event;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserMadeBasketOperation {
    protected long productId;
    protected long sellerId;
    protected long userId;
    protected final String topicName;

    protected UserMadeBasketOperation(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
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
