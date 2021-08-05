package com.microservices.ecommerce.promotion.service.eventModels;



import java.util.ArrayList;


public class Basket {
    private long userId;
    private ArrayList<Promotion> promotions;
    private ArrayList<Seller> sellers;

    public Basket() {
    }

    public Basket(long userId, ArrayList<Promotion> promotions, ArrayList<Seller> sellers) {
        this.userId = userId;
        this.promotions = promotions;
        this.sellers = sellers;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "userId=" + userId +
                ", promotions=" + promotions +
                ", sellers=" + sellers +
                '}';
    }
}
