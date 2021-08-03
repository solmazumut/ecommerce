package com.microservices.ecommerce.basket.service.model;

import com.microservices.ecommerce.basket.service.factory.UserFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
@Document
public class Seller {
    @Field
    private long sellerId;
    @Field
    private int stock;
    @Field
    private float price;
    @Field
    private List<User> users;

    public Seller(long sellerId, int stock, float price, List<User> users) {
        this.sellerId = sellerId;
        this.stock = stock;
        this.price = price;
        this.users = users;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private int getUserIndex(long userId) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserId() == userId) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addOrUpdateUser(User user) {
        int indexNotFound = -1;
        int userIndex = getUserIndex(user.getUserId());
        if(userIndex == indexNotFound) {
            users.add(user);
        } else {
            users.set(userIndex, user);
        }
    }

    public boolean deleteUser(long userId) {
        boolean isSuccessful = false;
        int indexNotFound = -1;
        int userIndex = getUserIndex(userId);
        if(userIndex != indexNotFound) {
            users.remove(userIndex);
            isSuccessful = true;
        }
        return isSuccessful;
    }
}
