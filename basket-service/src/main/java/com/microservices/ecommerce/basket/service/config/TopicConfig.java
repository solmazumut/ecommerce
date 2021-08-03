package com.microservices.ecommerce.basket.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic BasketProductInfoChangeTopic() {
        return TopicBuilder.name("basket-product-info-change")
                .partitions(6)
                .replicas(1)
                .build();
    }
}
