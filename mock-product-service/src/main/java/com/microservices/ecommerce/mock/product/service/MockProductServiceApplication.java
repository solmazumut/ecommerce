package com.microservices.ecommerce.mock.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.ecommerce")
public class MockProductServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MockProductServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MockProductServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


    }
}
