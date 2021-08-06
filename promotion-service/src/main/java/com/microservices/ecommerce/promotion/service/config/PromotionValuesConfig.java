package com.microservices.ecommerce.promotion.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "promotion-values")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class PromotionValuesConfig {
   private float nullValue;

    public float getNullValue() {
        return nullValue;
    }

    public void setNullValue(float nullValue) {
        this.nullValue = nullValue;
    }
}
