package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.config.TopicConfig;
import com.microservices.ecommerce.promotion.service.eventModels.Basket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final String basketTopicName = TopicConfig.getBasketUpdatedTopic();
    private final String groupId = TopicConfig.getGroupId();
    private final KafkaTemplate<String, Basket> basketKafkaTemplate;

    public KafkaService(KafkaTemplate<String, Basket> basketKafkaTemplate) {
        this.basketKafkaTemplate = basketKafkaTemplate;
    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "basketQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "basket-updated", partitions = { "0" })}
    )
    public void productPriceChangeKafkaListener(Basket basket) {
        System.out.println("event geldi");
        System.out.println(basket);
        System.out.println("basket user id " + basket.getUserId());
    }
}
