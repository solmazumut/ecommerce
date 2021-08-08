package com.microservices.ecommerce.mock.notification.service.models.impl;

import com.microservices.ecommerce.mock.notification.service.models.Notification;
import com.microservices.ecommerce.mock.notification.service.models.User;

public class WebPushNotification extends Notification {

    public WebPushNotification(long userId, String message, boolean isSend) {
        super(userId, message, isSend, false);
    }

    @Override
    public void checkSendingEnable(User user) {
        if(user.isWebPushNotificationActive()) {
            this.isEnable = true;
        }
    }
    @Override
    public boolean send() {
        //send notification and return result
        return didSend;
    }
}
