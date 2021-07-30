package com.microservices.ecommerce.basket.service.rest;

import com.microservices.ecommerce.basket.service.model.BasketProduct;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/demo")
public class DemoRestApiClass {

    private final KafkaTemplate<String, BasketProduct> kafkaTemplate;

    public DemoRestApiClass(KafkaTemplate<String, BasketProduct> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping(path = "/get-test-data")
    public String getTestData()
    {
        return "Hello Umut";
    }

    @PutMapping(path = "/send-event")
    public String sendEvent()
    {
        BasketProduct basketProductInfoChangeEvent = new BasketProduct();
        basketProductInfoChangeEvent.setProductName("test");
        kafkaTemplate.send("basket-product-info-change",basketProductInfoChangeEvent);
        return "test endpoint";

    }


}
