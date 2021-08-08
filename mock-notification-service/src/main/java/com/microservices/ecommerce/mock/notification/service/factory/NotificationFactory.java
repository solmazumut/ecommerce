package com.microservices.ecommerce.mock.notification.service.factory;

import com.microservices.ecommerce.mock.notification.service.models.Notification;
import com.microservices.ecommerce.mock.notification.service.models.User;
import com.microservices.ecommerce.mock.notification.service.models.impl.*;

import java.util.ArrayList;

public class NotificationFactory {
    public static ArrayList<Notification> createNotifications(User user, String message) {
        long userId = user.getUserId();
        ArrayList<Notification> notifications = new ArrayList<Notification>();
        Notification web = new WebPushNotification(userId, message, false);
        Notification mobile = new MobilePushNotification(userId, message, false);
        Notification sms = new Sms(userId, message, false);
        Notification voiceCall = new VoiceCall(userId, message, false);
        Notification email = new Email(userId, message, false);

        web.checkSendingEnable(user);
        mobile.checkSendingEnable(user);
        sms.checkSendingEnable(user);
        voiceCall.checkSendingEnable(user);
        email.checkSendingEnable(user);


        notifications.add(web);
        notifications.add(mobile);
        notifications.add(sms);
        notifications.add(voiceCall);
        notifications.add(email);

        return notifications;
    }
}
