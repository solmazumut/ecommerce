package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.eventModels.Basket;
import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.models.PromotionList;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionListService {
    private final PromotionRepository promotionRepository;
    private PromotionList promotionList;

    public PromotionListService( PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
        promotionList = new PromotionList(new ArrayList<Promotion>());
    }

    public void updatePromotionList() {
        List<Promotion> promotions =  this.promotionRepository.findAllByIsEnable();
        this.promotionList.setPromotions(promotions); ;
    }

    public Promotion findById(String id) {
        Promotion foundPromotion = null;
        updatePromotionList();
        long promotionId = Long.valueOf(id);
        for (Promotion promotion : this.promotionList.getPromotions()) {
            if(promotion.getPromotionId() == promotionId) {
                foundPromotion = promotion;
                break;
            }
        }
        return foundPromotion;
    }

    public ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> getPromotionsForBasket(Basket basket) {
        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                eventPromotionList = new ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>();
        updatePromotionList();
        for (Promotion promotion : this.promotionList.getPromotions()) {
            com.microservices.ecommerce.promotion.service.eventModels.Promotion
                    isThereEventPromotion = promotion.getPromotionForBasket(basket);
            if(isThereEventPromotion != null) {
                eventPromotionList.add(isThereEventPromotion);
                promotion.addUser(basket.getUserId());
            }
        }
        return eventPromotionList;
    }

    public Pair<Basket, Boolean> checkPromotionsForBasketAndUpdateDb(Basket basket) {
        boolean willItBeUpdated = false;


        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                eventPromotionList = getPromotionsForBasket(basket);

        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                newEventPromotions = newPromotions(basket.getPromotions(), eventPromotionList);

        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                promotionsNeedToBeDeleted = deletedPromotions(basket.getPromotions(), eventPromotionList);

        willItBeUpdated = checkIsDifferent(newEventPromotions, promotionsNeedToBeDeleted);

        if(willItBeUpdated) {
            basket = deletePromotionsFromBasketAndDB(basket, promotionsNeedToBeDeleted);
            basket = addPromotionsToBasketAndDB(basket, newEventPromotions);
        }

        Pair<Basket, Boolean> resultPair = Pair.of(basket, willItBeUpdated);

        return resultPair;
    }

    private boolean checkIsDifferent(
            ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> newEventPromotions,
            ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> promotionsNeedToBeDeleted) {
        boolean result = true;
        int sizeNewPromotions = newEventPromotions.size();
        int sizeDeletedPromotions = promotionsNeedToBeDeleted.size();
        if(sizeNewPromotions < 1 && sizeDeletedPromotions < 1) {
            result = false;
        }
        return result;
    }

    private Basket addPromotionsToBasketAndDB(Basket basket,
                        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> promotionsNeedToBeDeleted) {
        long userId = basket.getUserId();
        for (com.microservices.ecommerce.promotion.service.eventModels.Promotion eventPromotion : promotionsNeedToBeDeleted) {
            userAdd(eventPromotion.getPromotionId(), userId);
            basket.addPromotion(eventPromotion);
        }
        return basket;
    }

    private Basket deletePromotionsFromBasketAndDB(Basket basket,
                        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> promotionsNeedToBeDeleted) {
        long userId = basket.getUserId();
        for (com.microservices.ecommerce.promotion.service.eventModels.Promotion eventPromotion : promotionsNeedToBeDeleted) {
            long promotionId = eventPromotion.getPromotionId();
            userDelete(promotionId, userId);
            basket.deletePromotionWithId(promotionId);
        }
        return basket;
    }


    private ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
    newPromotions(ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> basketPromotions,
                   ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> newPromotions) {

        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                resultList = new ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>();

        for (com.microservices.ecommerce.promotion.service.eventModels.Promotion newPromotion : newPromotions ) {
            boolean isExist = false;
            for (com.microservices.ecommerce.promotion.service.eventModels.Promotion basketPromotion : basketPromotions) {
                if(basketPromotion.isSame(newPromotion)) {
                    isExist = true;
                    break;
                }
            }
            if(!isExist) {
                resultList.add(newPromotion);
            }
        }
        return resultList;
    }

    private ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
    deletedPromotions(ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> basketPromotions,
                  ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion> newPromotions) {

        ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>
                resultList = new ArrayList<com.microservices.ecommerce.promotion.service.eventModels.Promotion>();

        for (com.microservices.ecommerce.promotion.service.eventModels.Promotion basketPromotion : basketPromotions) {
            boolean isExist = false;
            for (com.microservices.ecommerce.promotion.service.eventModels.Promotion newPromotion : newPromotions) {
                if(basketPromotion.isSame(newPromotion)) {
                    isExist = true;
                    break;
                }
            }
            if(!isExist) {
                resultList.add(basketPromotion);
            }
        }
        return resultList;
    }

    public String userAdd(long promotionId, long userId) {
        try {
            Long newUserId = Long.valueOf(userId);
            String newPromotionId = String.valueOf(promotionId);
            Promotion promotion = findById(newPromotionId);
            promotion.addUser(newUserId);
            this.promotionRepository.save(promotion);
            return "User added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String userDelete(long promotionId, long userId) {
        try {
            Long newUserId = Long.valueOf(userId);
            String newPromotionId = String.valueOf(promotionId);
            Promotion promotion = findById(newPromotionId);
            promotion.deleteUser(newUserId);
            this.promotionRepository.save(promotion);
            return "User added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }
}
