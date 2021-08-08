package com.microservices.ecommerce.mock.product.service.services;

import com.microservices.ecommerce.mock.product.service.models.PriceChanged;
import com.microservices.ecommerce.mock.product.service.models.StockChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaService {
    @Autowired
    private final KafkaTemplate<String, PriceChanged> kafkaTemplatePriceChanged;
    private final KafkaTemplate<String, StockChanged> kafkaTemplateStockChanged;


    public KafkaService(KafkaTemplate<String, PriceChanged> kafkaTemplatePriceChanged,
                        KafkaTemplate<String, StockChanged> kafkaTemplateStockChanged) {
        this.kafkaTemplatePriceChanged = kafkaTemplatePriceChanged;
        this.kafkaTemplateStockChanged = kafkaTemplateStockChanged;
    }

    public void sendPriceChanged(PriceChanged message){
        System.out.println("Mesaj Gönderildi");
        this.kafkaTemplatePriceChanged.send("product-price-decreased",message);
    }

    public void sendStockChanged(StockChanged message){
        System.out.println("Mesaj Gönderildi");
        this.kafkaTemplateStockChanged.send("stock-decreased",message);
    }
}
