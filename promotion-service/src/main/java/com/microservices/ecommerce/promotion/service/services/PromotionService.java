package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final KafkaService kafkaService;

    public PromotionService(PromotionRepository promotionRepository, KafkaService kafkaService) {
        this.promotionRepository = promotionRepository;
        this.kafkaService = kafkaService;
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
            kafkaService.sendPromotionListChangedKafkaTemplate("New Promotion Added");
            return "Promotion is added";
        }
    }

    public String update(Promotion promotion) {
        try {
            String id = String.valueOf(promotion.getPromotionId());
            Promotion oldPromotion = findById(id);
            oldPromotion.update(promotion);
            this.promotionRepository.save(oldPromotion);
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
            return "Promotion updated";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String deletePromotion(Long promotionId) {
        try {
            String id = String.valueOf(promotionId);
            Promotion promotion = findById(id);
            this.promotionRepository.deleteById(id);
            ArrayList<Long> userList = promotion.getUsers();
            kafkaService.sendPromotionIsOverTopicKafkaTemplate(userList);
            return "Promotion deleted";
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
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
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
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
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
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
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
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
            return "Sellers deleted";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }


    public String addWhichUsers(long promotionId, ArrayList<Long> users) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.addWhichUsers(users);
            this.promotionRepository.save(oldPromotion);
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
            return "Users added";
        } catch (Exception e) {
            return "Promotion not found";
        }
    }

    public String deleteWhichUsers(long promotionId, ArrayList<Long> users) {
        try {
            String id = String.valueOf(promotionId);
            Promotion oldPromotion = findById(id);
            oldPromotion.deleteWhichUsers(users);
            this.promotionRepository.save(oldPromotion);
            kafkaService.sendPromotionListChangedKafkaTemplate("Promotion updated");
            return "Users deleted";
        } catch (Exception e) {
            return "Promotion not found";
        }
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
