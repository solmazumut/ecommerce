package com.microservices.ecommerce.basket.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
@Document
public class User {
    @Field
    private long userId;
    @Field
    private int quantity;

    public User(long userId, int quantity) {
        this.userId = userId;
        this.quantity = quantity;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantityOne() {
        int newQuantity = getQuantity() + 1;
        setQuantity(newQuantity);
    }
}
