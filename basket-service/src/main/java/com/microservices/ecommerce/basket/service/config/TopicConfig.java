package com.microservices.ecommerce.basket.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "topic-names")
public class TopicConfig {

    private static final String userMadeAddBasketOperation = "user-made-add-basket-operation";
    private static final String userMadeUpdateBasketOperation = "user-made-update-basket-operation";
    private static final String userMadeDeleteBasketOperation = "user-made-delete-basket-operation";
    private static final String outOfStock = "out-of-stock";
    private static final String stockDecreased = "stock-decreased";
    private static final String productRemovedFromBasket = "product-removed-from-basket";
    private static final String productPriceDecreased = "product-price-decreased";

    public static String getUserMadeAddBasketOperation() {
        return userMadeAddBasketOperation;
    }

    public static String getUserMadeUpdateBasketOperation() {
        return userMadeUpdateBasketOperation;
    }

    public static String getUserMadeDeleteBasketOperation() {
        return userMadeDeleteBasketOperation;
    }

    public static String getOutOfStock() { return outOfStock; }

    public static String getStockDecreased() { return stockDecreased; }

    public static String getProductRemovedFromBasket() { return productRemovedFromBasket; }

    public static String getProductPriceDecreased() { return productPriceDecreased; }

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
    @Bean
    public NewTopic ProductRemovedFromBasketTopic() {
        return TopicBuilder.name(getProductRemovedFromBasket())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic OutOfStockTopic() {
        return TopicBuilder.name(getOutOfStock())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic StockDecreasedTopic() {
        return TopicBuilder.name(getStockDecreased())
                .partitions(6)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic ProductPriceDecreasedTopic() {
        return TopicBuilder.name(getProductPriceDecreased())
                .partitions(6)
                .replicas(1)
                .build();
    }
}
