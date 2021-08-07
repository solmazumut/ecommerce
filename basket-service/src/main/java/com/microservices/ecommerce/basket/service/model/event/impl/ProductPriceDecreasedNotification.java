package com.microservices.ecommerce.basket.service.model.event.impl;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.NotificationEvents;

import java.util.ArrayList;

public class ProductPriceDecreasedNotification extends NotificationEvents {
    public ProductPriceDecreasedNotification(long productId, String productTitle, float oldPrice, float newPrice) {
        super(new ArrayList<Long>()," ", " ", productId, productTitle);
        String createdMessage = String
                .format("%d nolu %s isimli ürünün fiyatı %f TL'den %f TL'ye düştü!", productId, productTitle, oldPrice, newPrice);
        setMessage(createdMessage);

        String topic = TopicConfig.getProductPriceDecreased();
        setTopicName(topic);
    }
}
