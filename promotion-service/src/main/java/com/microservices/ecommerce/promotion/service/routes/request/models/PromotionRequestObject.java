package com.microservices.ecommerce.promotion.service.routes.request.models;

import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;

public class PromotionRequestObject {
    long promotionId;
    String promotionTitle;
    boolean isEnable;
    float discount;
    float totalPriceMax;
    float totalPriceMin;
    float sellerTotalPriceMax;
    float sellerTotalPriceMin;
    boolean isItEnoughOneSellerForPromotion;
    ArrayList<Long> whichUsers;
    ArrayList<Long> whichSellers;
    ArrayList<Long> whichProducts;

    public PromotionRequestObject() {
    }

    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotalPriceMax() {
        return totalPriceMax;
    }

    public void setTotalPriceMax(float totalPriceMax) {
        this.totalPriceMax = totalPriceMax;
    }

    public float getTotalPriceMin() {
        return totalPriceMin;
    }

    public void setTotalPriceMin(float totalPriceMin) {
        this.totalPriceMin = totalPriceMin;
    }

    public float getSellerTotalPriceMax() {
        return sellerTotalPriceMax;
    }

    public void setSellerTotalPriceMax(float sellerTotalPriceMax) {
        this.sellerTotalPriceMax = sellerTotalPriceMax;
    }

    public float getSellerTotalPriceMin() {
        return sellerTotalPriceMin;
    }

    public void setSellerTotalPriceMin(float sellerTotalPriceMin) {
        this.sellerTotalPriceMin = sellerTotalPriceMin;
    }

    public boolean isItEnoughOneSellerForPromotion() {
        return isItEnoughOneSellerForPromotion;
    }

    public void setItEnoughOneSellerForPromotion(boolean itEnoughOneSellerForPromotion) {
        isItEnoughOneSellerForPromotion = itEnoughOneSellerForPromotion;
    }

    public ArrayList<Long> getWhichUsers() {
        return whichUsers;
    }

    public void setWhichUsers(ArrayList<Long> whichUsers) {
        this.whichUsers = whichUsers;
    }

    public ArrayList<Long> getWhichSellers() {
        return whichSellers;
    }

    public void setWhichSellers(ArrayList<Long> whichSellers) {
        this.whichSellers = whichSellers;
    }

    public ArrayList<Long> getWhichProducts() {
        return whichProducts;
    }

    public void setWhichProducts(ArrayList<Long> whichProducts) {
        this.whichProducts = whichProducts;
    }
}
