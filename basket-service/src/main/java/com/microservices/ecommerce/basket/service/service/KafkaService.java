package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.config.NotificationConfig;
import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.model.User;
import com.microservices.ecommerce.basket.service.model.event.*;
import com.microservices.ecommerce.basket.service.model.event.impl.ProductPriceDecreasedNotification;
import com.microservices.ecommerce.basket.service.model.event.impl.StockDecreasedNotification;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeUpdateBasketOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaService {
    @Autowired
    private final KafkaTemplate<String, UserMadeBasketOperation> userMadeBasketOperationKafkaTemplate;
    private final KafkaTemplate<String, NotificationEvents> notificationEventsKafkaTemplate;
    private final ProductService productService;


    public KafkaService(KafkaTemplate<String, UserMadeBasketOperation> userMadeBasketOperationKafkaTemplate,
                        KafkaTemplate<String, NotificationEvents> notificationEventsKafkaTemplate, ProductService productService) {
        this.userMadeBasketOperationKafkaTemplate = userMadeBasketOperationKafkaTemplate;
        this.notificationEventsKafkaTemplate = notificationEventsKafkaTemplate;
        this.productService = productService;
    }

    public void sendUserMadeBasketOperationMessage(UserMadeBasketOperation message){
        System.out.println("Mesaj Gönderildi");
        String topicName = message.getTopicName();
        this.userMadeBasketOperationKafkaTemplate.send(topicName,message);
    }

    public void sendNotificationEventsMessage(NotificationEvents message){
        System.out.println("Mesaj Gönderildi");
        String topicName = message.getTopicName();
        this.notificationEventsKafkaTemplate.send(topicName,message);
    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "productPriceChangedQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "product-price-changed", partitions = { "0" })}
    )
    public void productPriceChangedKafkaListener(ProductPriceChanged message) {
        System.out.println("Mesaj Geldi");
        Pair<ArrayList<UserMadeUpdateBasketOperation>, Pair<String, Float>>
                occurredEvents = this.productService.getProductFollowerBasketEventsForPriceChangeAndUpdateDB(message);
        if(occurredEvents != null) {
            ArrayList<UserMadeUpdateBasketOperation> events = occurredEvents.getFirst();
            for (UserMadeUpdateBasketOperation event : events) {
                sendUserMadeBasketOperationMessage(event);
            }
            Float newPrice = message.getPrice();
            Pair<String, Float> productInfoPair = occurredEvents.getSecond();
            String productTitle = productInfoPair.getFirst();
            Float oldPrice = productInfoPair.getSecond();
            if(newPrice < oldPrice) {
                sendNotificationEventsMessage(new ProductPriceDecreasedNotification(message.getProductId(), productTitle, oldPrice, newPrice));
            }
        }

    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "productStockChangedQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "product-stock-changed", partitions = { "0" })}
    )
    public void productStockChangedKafkaListener(ProductStockChanged message) {
        System.out.println("Mesaj Geldi");
        //update DB
        long productId = message.getProductId();
        long sellerId = message.getSellerId();
        int productStock = message.getStock();
        Pair<Boolean, Product> respond = this.productService.updateProductStock(productId, sellerId, productStock);
        Boolean isDbUpdated = respond.getFirst();
        if(isDbUpdated) {
            Pair<NotificationEvents, ArrayList<UserMadeBasketOperation>>
                    respondEvents = this.productService
                    .checkStockAndUpdateDbAndReturnNotificationAndDbUpdateEvents(productId, sellerId);

            sendNotificationAndBasketOperationEventPair(respondEvents);

            checkStockDropBelowCertainNumberAndSendNotification(productId, sellerId, productStock, respond);


        }
    }

    private void checkStockDropBelowCertainNumberAndSendNotification(long productId, long sellerId, int productStock, Pair<Boolean, Product> respond) {
        NotificationConfig notificationConfigData = new NotificationConfig();
        int minStockLevelForNotification = notificationConfigData.getMinStockNumber();
        int stockIsOver = 0;
        if(stockIsOver < productStock && productStock <= minStockLevelForNotification) {
            Product product = respond.getSecond();
            Seller seller = product.getSellerWithId(sellerId);
            ArrayList<User> users = seller.getUsers();
            int thereIsNoUserWhoAddedProduct = 0;
            if(users.size() > thereIsNoUserWhoAddedProduct) {
                NotificationEvents stockDecreasedNotification = new StockDecreasedNotification(productId, product.getTitle(), productStock);
                for (User user : users) {
                    long userId = user.getUserId();
                    stockDecreasedNotification.addUser(userId);
                }
                sendNotificationEventsMessage(stockDecreasedNotification);
            }
        }
    }

    private void sendNotificationAndBasketOperationEventPair(Pair<NotificationEvents, ArrayList<UserMadeBasketOperation>> respondEvents) {
        NotificationEvents notification = respondEvents.getFirst();
        ArrayList<UserMadeBasketOperation> basketOperations = respondEvents.getSecond();

        if(notification != null) {
            sendNotificationEventsMessage(notification);
        }

        if (basketOperations != null) {
            for (UserMadeBasketOperation basketOperation : basketOperations) {
                sendUserMadeBasketOperationMessage(basketOperation);
            }
        }
    }


}
