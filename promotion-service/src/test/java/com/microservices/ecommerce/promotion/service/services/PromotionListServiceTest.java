package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.eventModels.Basket;
import com.microservices.ecommerce.promotion.service.eventModels.Product;
import com.microservices.ecommerce.promotion.service.eventModels.Seller;
import com.microservices.ecommerce.promotion.service.factory.PromotionFactory;
import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PromotionListServiceTest {
    @Autowired
    PromotionListService promotionListService;
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionRepository promotionRepository;

    @Test
    @Order(1)
    public void updatePromotionListAutomatically() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        String id = String.valueOf(promotion.getPromotionId());

        //When

        promotionService.create(promotion);
        Thread.sleep(50);
        Promotion foundPromotion = promotionListService.findById(id);

        //Then
        assertEquals(promotion.getPromotionId(), foundPromotion.getPromotionId());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(2)
    public void addPromotionBasketAndDB() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        long longId = promotion.getPromotionId();
        String stringId = String.valueOf(promotion.getPromotionId());
        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                basketPromotions = new ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>();
        Product basketProduct = new Product(1,"test","testUrl",4,50);
        ArrayList<Product> basketProducts = new ArrayList<Product>();
        basketProducts.add(basketProduct);
        Seller basketSeller = new Seller(1, basketProducts);
        ArrayList<Seller> basketSellers = new ArrayList<Seller>();
        basketSellers.add(basketSeller);
        Basket basket = new Basket(1, basketPromotions, basketSellers);

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                newBasketPromotions  = promotionListService.getPromotionsForBasket(basket);
        basket.setPromotions(newBasketPromotions);
        Thread.sleep(50);

        //Then
        boolean isExistPromotion = basket.isExistPromotion(longId);
        Promotion foundPromotion = promotionListService.findById(stringId);
        long userId = basket.getUserId();
        boolean isExistUser = foundPromotion.isExistUser(userId);
        assertTrue(isExistPromotion);
        assertTrue(isExistUser);
        Thread.sleep(50);
        promotionRepository.deleteById(stringId);
        Thread.sleep(50);
    }

}
