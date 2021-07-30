package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.BasketProduct;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, BasketProduct> kafkaTemplate;

    private String basketProductInfoChangeEventDestination = "demo-topic";

    public KafkaService(KafkaTemplate<String, BasketProduct> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(
            topics = "basket-product-info-change",
            groupId = "basket",
            containerFactory = "basketProductInfoChangeQueueKafkaListenerContainerFactory"
    )
    public void productPriceChangeKafkaListener(@Payload BasketProduct basketProductInfoChangeEvent) {
       System.out.println(basketProductInfoChangeEvent.getProductName());
    }





}
