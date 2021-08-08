package com.microservices.ecommerce.mock.notification.service.models;

public abstract class Notification {
    protected long userId;
    protected String message;
    protected boolean didSend;
    protected boolean isEnable;

    public Notification(long userId, String message, boolean isSend, boolean isEnable) {
        this.userId = userId;
        this.message = message;
        this.didSend = isSend;
        this.isEnable = isEnable;
    }

    public abstract void checkSendingEnable(User user);

    public abstract boolean send();

    public Notification() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isDidSend() {
        return didSend;
    }

    public void setDidSend(boolean didSend) {
        this.didSend = didSend;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
