package com.microservices.ecommerce.promotion.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "topic-names")
public class TopicConfig {


    private static String promotionInBasketChangedTopic = "promotion-in-basket-changed";
    private static String basketUpdatedTopic = "basket-updated";
    private static String promotionIsOverTopic = "promotion-is-over";
    private static String promotionListChanged = "promotion-list-changed";
    private static String groupId = "basket";

    public static String getPromotionInBasketChangedTopic() {
        return promotionInBasketChangedTopic;
    }

    public static String getBasketUpdatedTopic() {
        return basketUpdatedTopic;
    }

    public static String getPromotionIsOverTopic() {
        return promotionIsOverTopic;
    }

    public static String getPromotionListChanged() {
        return promotionListChanged;
    }

    public static String getGroupId() {
        return groupId;
    }

    @Bean
    public NewTopic PromotionUpdatedTopic() {
        return TopicBuilder.name(getPromotionInBasketChangedTopic())
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

    @Bean
    public NewTopic PromotionIsOverTopic() {
        return TopicBuilder.name(getPromotionIsOverTopic())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic PromotionListChanged() {
        return TopicBuilder.name(getPromotionListChanged())
                .partitions(6)
                .replicas(1)
                .build();
    }
}
