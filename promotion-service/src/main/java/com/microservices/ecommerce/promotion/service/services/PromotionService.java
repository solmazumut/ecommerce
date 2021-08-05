package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.eventModels.Product;
import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public Promotion findById(String id) {
        return promotionRepository.findById(id).orElseThrow( ()-> new RuntimeException(
                String.format("Promotion not found")
        ));
    }

    public String create(Promotion promotion) {
        try {
            String id = String.valueOf(promotion.getPromotionId());
            findById(id);
            return "Promotion has already exist";
        } catch (Exception e) {
            this.promotionRepository.save(promotion);
            return "Promotion is added";
        }
    }

    public String update(Promotion promotion) {
        try {
            String id = String.valueOf(promotion.getPromotionId());
            Promotion oldPromotion = findById(id);
            oldPromotion.update(promotion);
            this.promotionRepository.save(oldPromotion);
            return "Promotion updated";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String addProducts(long promotionId, ArrayList<Long> products) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.addProducts(products);
            this.promotionRepository.save(oldPromotion);
            return "Products added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String deleteProducts(long promotionId, ArrayList<Long> products) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.deleteProducts(products);
            this.promotionRepository.save(oldPromotion);
            return "Products deleted";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String addSellers(long promotionId, ArrayList<Long> sellers) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.addSellers(sellers);
            this.promotionRepository.save(oldPromotion);
            return "Sellers added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String deleteSellers(long promotionId, ArrayList<Long> sellers) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.deleteSellers(sellers);
            this.promotionRepository.save(oldPromotion);
            return "Sellers deleted";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String addUsers(long promotionId, ArrayList<Long> users) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.addUsers(users);
            this.promotionRepository.save(oldPromotion);
            return "Users added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String deleteUsers(long promotionId, ArrayList<Long> users) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.deleteUsers(users);
            this.promotionRepository.save(oldPromotion);
            return "Users deleted";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }
}
