package com.microservices.ecommerce.promotion.service.factory;

import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.routes.request.models.PromotionRequestObject;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;

public class PromotionFactory {
    public static Promotion createTestPromotion() {
        long promotionId = 535;
        String promotionTitle = "Kargo Kampanyası";
        boolean isEnable = true;
        float discount = 11.9f;
        float totalPriceMax = -1;
        float totalPriceMin = -1;
        float sellerTotalPriceMax = -1;
        float sellerTotalPriceMin = 50;
        boolean isItEnoughOneSellerForPromotion = false;
        ArrayList<Long> whichUsers = new ArrayList<Long>();
        ArrayList<Long> whichSellers = new ArrayList<Long>();
        ArrayList<Long> whichProducts = new ArrayList<Long>();
        ArrayList<Long> users = new ArrayList<Long>();
        Promotion promotion = new Promotion(promotionId, promotionTitle, isEnable, discount, totalPriceMax, totalPriceMin,
                                            sellerTotalPriceMax, sellerTotalPriceMin, isItEnoughOneSellerForPromotion,
                                            whichUsers, whichSellers, whichProducts, users);
        return promotion;
    }
    public static Promotion createPromotionFromRequestObject(PromotionRequestObject promotionRequestObject) {
        long promotionId = promotionRequestObject.getPromotionId();
        String promotionTitle = promotionRequestObject.getPromotionTitle();
        boolean isEnable = promotionRequestObject.isEnable();
        float discount = promotionRequestObject.getDiscount();
        float totalPriceMax = promotionRequestObject.getTotalPriceMax();
        float totalPriceMin = promotionRequestObject.getTotalPriceMin();
        float sellerTotalPriceMax = promotionRequestObject.getSellerTotalPriceMax();
        float sellerTotalPriceMin = promotionRequestObject.getSellerTotalPriceMin();
        boolean isItEnoughOneSellerForPromotion = promotionRequestObject.isItEnoughOneSellerForPromotion();
        ArrayList<Long> whichUsers = promotionRequestObject.getWhichUsers();
        ArrayList<Long> whichSellers = promotionRequestObject.getWhichSellers();
        ArrayList<Long> whichProducts = promotionRequestObject.getWhichProducts();
        ArrayList<Long> users = new ArrayList<Long>();

        Promotion promotion = new Promotion(promotionId, promotionTitle, isEnable, discount, totalPriceMax, totalPriceMin,
                sellerTotalPriceMax, sellerTotalPriceMin, isItEnoughOneSellerForPromotion,
                whichUsers, whichSellers, whichProducts, users);
        return promotion;
    }
}
