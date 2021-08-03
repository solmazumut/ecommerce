package com.microservices.ecommerce.basket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.ecommerce")
public class BasketServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(BasketServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BasketServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

            LOG.info("Basket Service starts...");

    }
}
