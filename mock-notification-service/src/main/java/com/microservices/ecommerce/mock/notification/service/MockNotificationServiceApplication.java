package com.microservices.ecommerce.mock.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

public class MockNotificationServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MockNotificationServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MockNotificationServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        LOG.info("Mock Notification Service starts...");

    }
}
