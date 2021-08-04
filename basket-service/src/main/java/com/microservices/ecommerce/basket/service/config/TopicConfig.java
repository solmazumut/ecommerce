package com.microservices.ecommerce.basket.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "topic-names")
public class TopicConfig {

    private static String userMadeAddBasketOperation = "user-made-add-basket-operation";
    private static String userMadeUpdateBasketOperation = "user-made-update-basket-operation";
    private static String userMadeDeleteBasketOperation = "user-made-delete-basket-operation";

    public static String getUserMadeAddBasketOperation() {
        return userMadeAddBasketOperation;
    }

    public static String getUserMadeUpdateBasketOperation() {
        return userMadeUpdateBasketOperation;
    }

    public static String getUserMadeDeleteBasketOperation() {
        return userMadeDeleteBasketOperation;
    }



    @Bean
    public NewTopic UserMadeAddBasketOperationTopic() {
        return TopicBuilder.name(getUserMadeAddBasketOperation())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic UserMadeUpdateBasketOperationTopic() {
        return TopicBuilder.name(getUserMadeUpdateBasketOperation())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic UserMadeDeleteBasketOperationTopic() {
        return TopicBuilder.name(getUserMadeDeleteBasketOperation())
                .partitions(6)
                .replicas(1)
                .build();
    }
}
