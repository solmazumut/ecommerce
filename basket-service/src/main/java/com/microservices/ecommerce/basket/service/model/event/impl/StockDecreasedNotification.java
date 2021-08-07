package com.microservices.ecommerce.basket.service.model.event.impl;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.NotificationEvents;

import java.util.ArrayList;

public class StockDecreasedNotification extends NotificationEvents {
    public StockDecreasedNotification(long productId, String productTitle, int stock) {
        super(new ArrayList<Long>()," ", " ", productId, productTitle);
        String createdMessage = String
                .format("%d nolu %s isimli ürün stoğu azalıyor! Son %d ürün.", productId, productTitle, stock);
        setMessage(createdMessage);

        String topic = TopicConfig.getStockDecreased();
        setTopicName(topic);
    }
}
