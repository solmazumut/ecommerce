package com.microservices.ecommerce.basket.service.model.event.impl;

import com.microservices.ecommerce.basket.service.config.TopicConfig;
import com.microservices.ecommerce.basket.service.model.event.NotificationEvents;

import java.util.ArrayList;

public class OutOfStockNotification extends NotificationEvents {
    public OutOfStockNotification(long productId, String productTitle) {
        super(new ArrayList<Long>()," ", " ", productId, productTitle);
        String createdMessage = String
                .format("%d nolu %s isimli ürünün stoğu bitti bu yüzden sepetinizden çıkarıldı.", productId, productTitle);
        setMessage(createdMessage);

        String topic = TopicConfig.getOutOfStock();
        setTopicName(topic);
    }
}
