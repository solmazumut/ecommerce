package com.microservices.ecommerce.promotion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.ecommerce")
public class PromotionServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PromotionServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PromotionServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        LOG.info("Promotion Service starts...");

    }
}
