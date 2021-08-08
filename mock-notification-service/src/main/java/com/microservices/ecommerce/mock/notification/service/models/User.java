package com.microservices.ecommerce.mock.notification.service.models;

import java.util.ArrayList;

public class User {
    private long userId;
    private boolean isMobilePushNotificationActive;
    private boolean isWebPushNotificationActive;
    private boolean isVoiceCallActive;
    private boolean isEmailActive;
    private boolean isSmsActive;
    private ArrayList<Notification> notifications;

    public User() {
    }

    public User(long userId, boolean isMobilePushNotificationActive, boolean isWebPushNotificationActive,
                boolean isVoiceCallActive, boolean isEmailActive, boolean isSmsActive, ArrayList<Notification> notifications) {
        this.userId = userId;
        this.isMobilePushNotificationActive = isMobilePushNotificationActive;
        this.isWebPushNotificationActive = isWebPushNotificationActive;
        this.isVoiceCallActive = isVoiceCallActive;
        this.isEmailActive = isEmailActive;
        this.isSmsActive = isSmsActive;
        this.notifications = notifications;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isMobilePushNotificationActive() {
        return isMobilePushNotificationActive;
    }

    public void setMobilePushNotificationActive(boolean mobilePushNotificationActive) {
        isMobilePushNotificationActive = mobilePushNotificationActive;
    }

    public boolean isWebPushNotificationActive() {
        return isWebPushNotificationActive;
    }

    public void setWebPushNotificationActive(boolean webPushNotificationActive) {
        isWebPushNotificationActive = webPushNotificationActive;
    }

    public boolean isVoiceCallActive() {
        return isVoiceCallActive;
    }

    public void setVoiceCallActive(boolean voiceCallActive) {
        isVoiceCallActive = voiceCallActive;
    }

    public boolean isEmailActive() {
        return isEmailActive;
    }

    public void setEmailActive(boolean emailActive) {
        isEmailActive = emailActive;
    }

    public boolean isSmsActive() {
        return isSmsActive;
    }

    public void setSmsActive(boolean smsActive) {
        isSmsActive = smsActive;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(Notification notification) {
        if(this.notifications == null) {
            this.notifications = new ArrayList<Notification>();
        }
        this.notifications.add(notification);
    }

    public boolean notificationDidSend(Notification notification) {
        boolean result = false;
        notification.setDidSend(true);
        result = this.notifications.contains(notification);
        notification.setDidSend(false);
        return result;
    }
}
