package com.microservices.ecommerce.promotion.service.models;

import java.util.ArrayList;

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
}
