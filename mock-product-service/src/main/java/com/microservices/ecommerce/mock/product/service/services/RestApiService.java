package com.microservices.ecommerce.mock.product.service.services;

import com.microservices.ecommerce.mock.product.service.models.PriceChanged;
import com.microservices.ecommerce.mock.product.service.models.StockChanged;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/product")
public class RestApiService {

    private final KafkaService kafkaService;

    public RestApiService(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }


    @PostMapping(path = "/price-changed")
    public @ResponseBody String  priceChange(PriceChanged priceChanged)
    {
        kafkaService.sendPriceChanged(priceChanged);
        return "send event";
    }

    @PostMapping(path = "/stock-changed")
    public @ResponseBody String  stockChange(StockChanged stockChanged)
    {
        kafkaService.sendStockChanged(stockChanged);
        return "send event";
    }

}
