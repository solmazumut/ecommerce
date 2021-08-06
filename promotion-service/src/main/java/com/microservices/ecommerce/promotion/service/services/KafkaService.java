package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.config.TopicConfig;
import com.microservices.ecommerce.promotion.service.eventModels.Basket;
import org.springframework.data.util.Pair;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaService {

    private final String basketTopicName = TopicConfig.getBasketUpdatedTopic();
    private final String groupId = TopicConfig.getGroupId();
    private final KafkaTemplate<String, Basket> basketKafkaTemplate;
    private final KafkaTemplate<String, String> promotionListChangedKafkaTemplate;
    private final KafkaTemplate<String, ArrayList<Long>> promotionIsOverTopicKafkaTemplate;
    private final PromotionListService promotionListService;

    public KafkaService(KafkaTemplate<String, Basket> basketKafkaTemplate,
                        KafkaTemplate<String, String> promotionListChangedKafkaTemplate,
                        KafkaTemplate<String, ArrayList<Long>> promotionIsOverTopicKafkaTemplate,
                        PromotionListService promotionListService) {
        this.basketKafkaTemplate = basketKafkaTemplate;
        this.promotionListChangedKafkaTemplate = promotionListChangedKafkaTemplate;
        this.promotionIsOverTopicKafkaTemplate = promotionIsOverTopicKafkaTemplate;
        this.promotionListService = promotionListService;
    }

    public void sendPromotionUpdatedMessage(Basket basket) {
        System.out.println("Mesaj Gönderildi");
        String topicName = TopicConfig.getPromotionInBasketChangedTopic();
        this.basketKafkaTemplate.send(topicName,basket);
    }

    public void sendPromotionListChangedKafkaTemplate(String message) {
        System.out.println("Mesaj Gönderildi");
        String topicName = TopicConfig.getPromotionListChanged();
        this.promotionListChangedKafkaTemplate.send(topicName,message);
    }

    public void sendPromotionIsOverTopicKafkaTemplate(ArrayList<Long> userList) {
        System.out.println("Mesaj Gönderildi");
        String topicName = TopicConfig.getPromotionIsOverTopic();
        this.promotionIsOverTopicKafkaTemplate.send(topicName,userList);
    }



    @KafkaListener(
            groupId = "basket",
            containerFactory = "basketQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "basket-updated", partitions = { "0" })}
    )
    public void productPriceChangeKafkaListener(Basket basket) {
        Pair<Basket, Boolean> respond = promotionListService.checkPromotionsForBasketAndUpdateDb(basket);
        boolean wasItUpdated = respond.getSecond();
        if(wasItUpdated) {
            Basket newBasket = respond.getFirst();
            sendPromotionUpdatedMessage(newBasket);
        }
    }
}
