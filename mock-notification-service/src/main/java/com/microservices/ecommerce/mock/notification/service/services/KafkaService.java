package com.microservices.ecommerce.mock.notification.service.services;

import com.microservices.ecommerce.mock.notification.service.factory.NotificationFactory;
import com.microservices.ecommerce.mock.notification.service.models.BasketNotificationEvent;
import com.microservices.ecommerce.mock.notification.service.models.Notification;
import com.microservices.ecommerce.mock.notification.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaService {
    @Autowired
    private final MockDBService mockDBService;

    public KafkaService(MockDBService mockDBService) {
        this.mockDBService = mockDBService;
    }


    @KafkaListener(
            groupId = "basket",
            containerFactory = "basketNotificationEventQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "stock-decreased", partitions = { "0" })}
    )
    public void stockDecreased(BasketNotificationEvent message) {
        sendNotificationAndSaveDB(message);
    }

    @KafkaListener(
            groupId = "basket",
            containerFactory = "basketNotificationEventQueueKafkaListenerContainerFactory",
            topicPartitions = { @TopicPartition(topic = "product-price-decreased", partitions = { "0" })}
    )
    public void priceDecreased(BasketNotificationEvent message) {
        sendNotificationAndSaveDB(message);
    }

    private void sendNotificationAndSaveDB(BasketNotificationEvent message) {
        ArrayList<User> users = mockDBService.getUsersWithId(message.getUserIdList());
        for (User user : users) {
            ArrayList<Notification> notifications = NotificationFactory
                    .createNotifications(user, message.getMessage());

            for (Notification notification : notifications) {
                if(notification.isEnable()) {
                    boolean didSend = user.notificationDidSend(notification);
                    if(!didSend) {
                        boolean result = notification.send();
                        if(result) {
                            user.addNotification(notification);
                            mockDBService.updateUser(user);
                        }
                    }
                }
            }
        }
    }


}
