package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.factory.PromotionFactory;
import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PromotionServiceTest {
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionRepository promotionRepository;

    @Test
    @Order(1)
    public void findPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        String id = String.valueOf(promotion.getPromotionId());

        //When

        promotionService.create(promotion);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(promotion.getPromotionId(), foundPromotion.getPromotionId());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(2)
    public void updatePromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        promotion.setEnable(true);
        Promotion promotion2 = PromotionFactory.createTestPromotion();
        promotion2.setEnable(false);
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.update(promotion2);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(promotion2.isEnable(), foundPromotion.isEnable());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(3)
    public void addProductToPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        promotion.setWhichProducts(new ArrayList<Long>());
        ArrayList<Long> products = new ArrayList<Long>();
        products.add(Long.valueOf(1));
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.addProducts(promotion.getPromotionId(), products);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(products.get(0), foundPromotion.getWhichProducts().get(0));
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(4)
    public void deleteProductFromPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        ArrayList<Long> products = new ArrayList<Long>();
        products.add(Long.valueOf(1));
        promotion.setWhichProducts(products);
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.deleteProducts(promotion.getPromotionId(), products);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(0, foundPromotion.getWhichProducts().size());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(5)
    public void addSellerToPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        promotion.setWhichSellers(new ArrayList<Long>());
        ArrayList<Long> sellers = new ArrayList<Long>();
        sellers.add(Long.valueOf(1));
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.addSellers(promotion.getPromotionId(), sellers);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(sellers.get(0), foundPromotion.getWhichSellers().get(0));
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(6)
    public void deleteSellerFromPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        ArrayList<Long> sellers = new ArrayList<Long>();
        sellers.add(Long.valueOf(1));
        promotion.setWhichSellers(sellers);
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.deleteSellers(promotion.getPromotionId(), sellers);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(0, foundPromotion.getWhichSellers().size());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(7)
    public void addWhichUserToPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        promotion.setWhichUsers(new ArrayList<Long>());
        ArrayList<Long> users = new ArrayList<Long>();
        users.add(Long.valueOf(1));
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.addUsers(promotion.getPromotionId(), users);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(users.get(0), foundPromotion.getWhichUsers().get(0));
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }

    @Test
    @Order(8)
    public void deleteWhichUserFromPromotion() throws InterruptedException {

        //Given
        Promotion promotion = PromotionFactory.createTestPromotion();
        ArrayList<Long> users = new ArrayList<Long>();
        users.add(Long.valueOf(1));
        promotion.setWhichUsers(users);
        String id = String.valueOf(promotion.getPromotionId());

        //When
        promotionService.create(promotion);
        Thread.sleep(50);
        promotionService.deleteUsers(promotion.getPromotionId(), users);
        Thread.sleep(50);
        Promotion foundPromotion = promotionService.findById(id);

        //Then
        assertEquals(0, foundPromotion.getWhichUsers().size());
        Thread.sleep(50);
        promotionRepository.deleteById(id);
        Thread.sleep(50);
    }


}
