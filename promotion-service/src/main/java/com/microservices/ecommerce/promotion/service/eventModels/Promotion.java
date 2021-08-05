package com.microservices.ecommerce.promotion.service.eventModels;



public class Promotion {
    private long promotionId;
    private String promotionTitle;
    private float discountPrice;

    public Promotion() {
    }

    public Promotion(long promotionId, String promotionTitle, float discountPrice) {
        this.promotionId = promotionId;
        this.promotionTitle = promotionTitle;
        this.discountPrice = discountPrice;
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

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promotionId=" + promotionId +
                ", promotionTitle='" + promotionTitle + '\'' +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
