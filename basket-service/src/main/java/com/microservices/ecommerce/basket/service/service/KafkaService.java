package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.event.UserMadeAddBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeDeleteBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeUpdateBasketOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private final KafkaTemplate<String, UserMadeBasketOperation> userMadeBasketOperationKafkaTemplate;


    public KafkaService(KafkaTemplate<String, UserMadeBasketOperation> userMadeBasketOperationKafkaTemplate) {
        this.userMadeBasketOperationKafkaTemplate = userMadeBasketOperationKafkaTemplate;
    }

    public void sendUserMadeBasketOperationMessage(UserMadeBasketOperation message){
        System.out.println("Mesaj Gönderildi");
        String topicName = message.getTopicName();
        this.userMadeBasketOperationKafkaTemplate.send(topicName,message);
    }
/*
    @KafkaListener(
            groupId = "basket",
            containerFactory = "userMadeBasketOperationQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "user-made-add-basket-operation", partitions = { "0" })}
    )
    public void UserMadeAddBasketOperationKafkaListener(@Payload UserMadeAddBasketOperation userMadeAddBasketOperation) {
       System.out.println(userMadeAddBasketOperation.getProductTitle());
    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "userMadeBasketOperationQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "user-made-update-basket-operation", partitions = { "0" })}
    )
    public void UserMadeUpdateBasketOperationKafkaListener(@Payload UserMadeUpdateBasketOperation userMadeUpdateBasketOperation) {
        System.out.println(userMadeUpdateBasketOperation.getProductTitle());
    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "userMadeBasketOperationQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "user-made-delete-basket-operation", partitions = { "0" })}
    )
    public void UserMadeDeleteBasketOperationKafkaListener(@Payload UserMadeDeleteBasketOperation userMadeDeleteBasketOperation) {
        System.out.println(userMadeDeleteBasketOperation.getProductId());
    }

*/




}
