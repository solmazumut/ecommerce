package com.microservices.ecommerce.basket.service.model.event.impl;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.NotificationEvents;

import java.util.ArrayList;

public class ProductRemovedFromBasketNotification extends NotificationEvents {

    public ProductRemovedFromBasketNotification(long productId, String productTitle) {
        super(new ArrayList<Long>()," ", " ", productId, productTitle);
        String createdMessage = String
                .format("%d nolu %s isimli ürün stoğu azaldığı için sepetinizden çıkarıldı", productId, productTitle);
        setMessage(createdMessage);

        String topic = TopicConfig.getProductRemovedFromBasket();
        setTopicName(topic);
    }
}
