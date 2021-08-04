package com.microservices.ecommerce.basket.service.model;

import com.microservices.ecommerce.basket.service.factory.UserFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
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
    private ArrayList<User> users;

    public Seller(long sellerId, int stock, float price, ArrayList<User> users) {
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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
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

    public boolean isUserExist(long userId) {
        boolean userExist = false;
        for (User userAddedSeller : users) {
            long userIdAddedSeller = userAddedSeller.getUserId();
            if (userIdAddedSeller == userId) {
                userExist = true;
                break;
            }
        }
        return userExist;
    }

    public void addOrUpdateUserOneQuantity(long userId) {
        int indexNotFound = -1;
        int userIndex = getUserIndex(userId);
        if(userIndex == indexNotFound) {
            User user = UserFactory.createUser(userId);
            users.add(user);
        } else {
            User user = users.get(userIndex);
            user.increaseQuantityOne();
            users.set(userIndex, user);
        }
    }

    public boolean isAnyUserAddedSeller() {
        boolean result = true;
        if (this.users.size() < 1) {
            result = false;
        }
        return result;
    }
}
