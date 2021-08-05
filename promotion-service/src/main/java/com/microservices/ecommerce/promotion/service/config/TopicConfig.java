package com.microservices.ecommerce.promotion.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "topic-names")
public class TopicConfig {


    private static String addPromotionTopic = "add-promotion";
    private static String deletePromotionTopic = "delete-promotion";
    private static String basketUpdatedTopic = "basket-updated";
    private static String groupId = "basket";

    public static String getAddPromotionTopic() {
        return addPromotionTopic;
    }

    public static String getDeletePromotionTopic() {
        return deletePromotionTopic;
    }

    public static String getBasketUpdatedTopic() {
        return basketUpdatedTopic;
    }

    public static String getGroupId() {
        return groupId;
    }

    @Bean
    public NewTopic AddPromotionTopic() {
        return TopicBuilder.name(getAddPromotionTopic())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic DeletePromotionTopic() {
        return TopicBuilder.name(getDeletePromotionTopic())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic BasketUpdatedTopic() {
        return TopicBuilder.name(getBasketUpdatedTopic())
                .partitions(6)
                .replicas(1)
                .build();
    }
}
