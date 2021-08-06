package com.microservices.ecommerce.promotion.service.models;

import java.util.ArrayList;
import java.util.List;

public class PromotionList {
    ArrayList<Promotion> promotions;

    public PromotionList(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
    public void setPromotions(List<Promotion> promotions) {
        if(promotions != null) {
            ArrayList<Promotion> arrayListPromotion = new ArrayList<>();
            for (Promotion promotion : promotions) {
                arrayListPromotion.add(promotion);
            }
            this.promotions = arrayListPromotion;
        }
    }
}
