package com.microservices.ecommerce.mock.notification.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@ConfigurationProperties(prefix = "topic-names")
public class TopicConfig {

    private static final String stockDecreased = "stock-decreased";
    private static final String productPriceDecreased = "product-price-decreased";
    private static final String outOfStock = "out-of-stock";
    private static final String productRemovedFromBasket = "product-removed-from-basket";

    public static String getStockDecreased() { return stockDecreased; }

    public static String getProductPriceDecreased() { return productPriceDecreased; }

    public static String getOutOfStock() {
        return outOfStock;
    }

    public static String getProductRemovedFromBasket() {
        return productRemovedFromBasket;
    }
}
