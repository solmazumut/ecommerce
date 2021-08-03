package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.event.BasketProductInfoChangeEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, BasketProductInfoChangeEvent> kafkaTemplate;

    private String basketProductInfoChangeEventDestination = "demo-topic";

    public KafkaService(KafkaTemplate<String, BasketProductInfoChangeEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @KafkaListener(
            groupId = "basket",
            containerFactory = "basketProductInfoChangeQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "basket-product-info-change", partitions = { "0" })}
    )
    public void productPriceChangeKafkaListener(@Payload BasketProductInfoChangeEvent basketProductInfoChangeEvent) {
       System.out.println(basketProductInfoChangeEvent.getProductName());
    }





}
