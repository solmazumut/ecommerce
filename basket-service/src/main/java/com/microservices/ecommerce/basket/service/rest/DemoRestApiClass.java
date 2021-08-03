package com.microservices.ecommerce.basket.service.rest;

import com.microservices.ecommerce.basket.service.model.event.BasketProductInfoChangeEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/demo")
public class DemoRestApiClass {

    private final KafkaTemplate<String, BasketProductInfoChangeEvent> kafkaTemplate;


    public DemoRestApiClass(KafkaTemplate<String, BasketProductInfoChangeEvent> kafkaTemplate) {
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
        BasketProductInfoChangeEvent basketProductInfoChangeEvent = new BasketProductInfoChangeEvent();
        basketProductInfoChangeEvent.setProductName("go beni duyuyor musun?");
        for (int part = 0; part < 2; part++) {
            kafkaTemplate.send(new ProducerRecord<String, BasketProductInfoChangeEvent>("basket-product-info-change", part, "basket", basketProductInfoChangeEvent));
        }
        //kafkaTemplate.send("basket-product-info-change",basketProductInfoChangeEvent);
        return "test endpoint";

    }


}
