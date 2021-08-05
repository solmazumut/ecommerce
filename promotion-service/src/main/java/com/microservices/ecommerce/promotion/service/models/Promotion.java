package com.microservices.ecommerce.promotion.service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;

@Document
public class Promotion {
    @Id
    long promotionId;
    @Field
    String promotionTitle;
    @Field
    boolean isEnable;
    @Field
    float discount;
    @Field
    float totalPriceMax;
    @Field
    float totalPriceMin;
    @Field
    float sellerTotalPriceMax;
    @Field
    float sellerTotalPriceMin;
    @Field
    boolean isItEnoughOneSellerForPromotion;
    @Field
    ArrayList<Long> whichUsers;
    @Field
    ArrayList<Long> whichSellers;
    @Field
    ArrayList<Long> whichProducts;
    @Field
    ArrayList<Long> users;

    public Promotion() {
    }

    public Promotion(long promotionId, String promotionTitle, boolean isEnable,
                     float discount, float totalPriceMax, float totalPriceMin,
                     float sellerTotalPriceMax, float sellerTotalPriceMin,
                     boolean isItEnoughOneSellerForPromotion, ArrayList<Long> whichUsers,
                     ArrayList<Long> whichSellers, ArrayList<Long> whichProducts, ArrayList<Long> users) {
        this.promotionId = promotionId;
        this.promotionTitle = promotionTitle;
        this.isEnable = isEnable;
        this.discount = discount;
        this.totalPriceMax = totalPriceMax;
        this.totalPriceMin = totalPriceMin;
        this.sellerTotalPriceMax = sellerTotalPriceMax;
        this.sellerTotalPriceMin = sellerTotalPriceMin;
        this.isItEnoughOneSellerForPromotion = isItEnoughOneSellerForPromotion;
        this.whichUsers = whichUsers;
        this.whichSellers = whichSellers;
        this.whichProducts = whichProducts;
        this.users = users;
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

    public ArrayList<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
    }

    public void update(Promotion promotion) {
        String promotionTitle = promotion.getPromotionTitle();
        boolean isEnable = promotion.isEnable();
        float discount = promotion.getDiscount();
        float totalPriceMax = promotion.getTotalPriceMax();
        float totalPriceMin = promotion.getTotalPriceMin();
        float sellerTotalPriceMax = promotion.getSellerTotalPriceMax();
        float sellerTotalPriceMin = promotion.getSellerTotalPriceMin();
        boolean isItEnoughOneSellerForPromotion = promotion.isItEnoughOneSellerForPromotion();
        ArrayList<Long> whichUsers = promotion.getWhichUsers();
        ArrayList<Long> whichSellers = promotion.getWhichSellers();
        ArrayList<Long> whichProducts = promotion.getWhichProducts();

        setPromotionTitle(promotionTitle);
        setEnable(isEnable);
        setDiscount(discount);
        setTotalPriceMax(totalPriceMax);
        setTotalPriceMin(totalPriceMin);
        setSellerTotalPriceMax(sellerTotalPriceMax);
        setSellerTotalPriceMin(sellerTotalPriceMin);
        setItEnoughOneSellerForPromotion(isItEnoughOneSellerForPromotion);
        setWhichUsers(whichUsers);
        setWhichSellers(whichSellers);
        setWhichProducts(whichProducts);
    }

    public void addProducts(ArrayList<Long> products) {
        for (Long productId: products) {
            boolean isProductExist = this.whichProducts.contains(productId);
            if(!isProductExist) {
                this.whichProducts.add(productId);
            }
        }
    }

    public void deleteProducts(ArrayList<Long> products) {
        for (Long productId: products) {
            boolean isProductExist = this.whichProducts.contains(productId);
            if(isProductExist) {
                this.whichProducts.remove(productId);
            }
        }
    }

    public void addSellers(ArrayList<Long> sellers) {
        for (Long sellerId: sellers) {
            boolean isSellerExist = this.whichSellers.contains(sellerId);
            if(!isSellerExist) {
                this.whichSellers.add(sellerId);
            }
        }
    }

    public void deleteSellers(ArrayList<Long> sellers) {
        for (Long sellerId: sellers) {
            boolean isSellerExist = this.whichSellers.contains(sellerId);
            if(isSellerExist) {
                this.whichSellers.remove(sellerId);
            }
        }
    }

    public void addUsers(ArrayList<Long> users) {
        for (Long userId: users) {
            boolean isUserExist = this.whichUsers.contains(userId);
            if(!isUserExist) {
                this.whichUsers.add(userId);
            }
        }
    }

    public void deleteUsers(ArrayList<Long> users) {
        for (Long userId: users) {
            boolean isUserExist = this.whichUsers.contains(userId);
            if(isUserExist) {
                this.whichUsers.remove(userId);
            }
        }
    }
}
