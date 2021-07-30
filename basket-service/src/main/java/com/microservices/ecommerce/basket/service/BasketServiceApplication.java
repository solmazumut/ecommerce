package com.microservices.ecommerce.basket.service;

import com.microservices.ecommerce.basket.service.model.BasketProduct;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

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
        MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "Baskets");
        mongoOps.insert(new BasketProduct());
        BasketProduct aa = new BasketProduct();
        aa.setPrice(12);
        MongoOperations mongoOps2 = new MongoTemplate(MongoClients.create(), "FollowingUsers");
        mongoOps2.insert(aa);
    }
}
