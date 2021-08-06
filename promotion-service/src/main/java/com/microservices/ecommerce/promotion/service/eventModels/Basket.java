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

    public void addPromotion(Promotion promotion) {
        if(promotions == null) {
            promotions = new ArrayList<Promotion>();
        }
        promotions.add(promotion);
    }

    public void deleteAllPromotions() {
        promotions = new ArrayList<Promotion>();
    }

    public float getTotalPrice() {
        float price = 0;
        if(this.sellers != null) {
            for (Seller seller : this.sellers) {
                price += seller.getTotalPrice();
            }
        }
        return price;
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> allProducts = new ArrayList<Product>();
        if(this.sellers != null) {
            for (Seller seller : this.sellers) {
                ArrayList<Product> sellerProducts = seller.getProducts();
                if(sellerProducts != null) {
                    allProducts.addAll(sellerProducts);
                }
            }
        }
        return allProducts;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "userId=" + userId +
                ", promotions=" + promotions +
                ", sellers=" + sellers +
                '}';
    }

    public boolean isExistPromotion(long id) {
        boolean result = false;
        for (Promotion promotion : this.promotions) {
            if (promotion.getPromotionId() == id) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void deletePromotionWithId(long promotionId) {
        if(promotions != null) {
            int indexNotFound = -1;
            int index = getPromotionIndex(promotionId);
            if(index != indexNotFound) {
                promotions.remove(index);
            }
        }
    }

    private int getPromotionIndex(long promotionId) {
        int index = -1;
        if(promotions != null) {
            int counter = 0;
            for (Promotion promotion : promotions) {
                if(promotionId == promotion.getPromotionId()) {
                    index = counter;
                    break;
                }
                counter++;
            }
        }
        return index;
    }
}
