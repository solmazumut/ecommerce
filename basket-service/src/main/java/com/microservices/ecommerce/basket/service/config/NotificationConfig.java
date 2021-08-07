package com.microservices.ecommerce.basket.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "notification-config")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class NotificationConfig {
    private int minStockNumber;

    public int getMinStockNumber() {
        return minStockNumber;
    }

    public  void setMinStockNumber(int minStockNumber) {
        this.minStockNumber = minStockNumber;
    }
}
