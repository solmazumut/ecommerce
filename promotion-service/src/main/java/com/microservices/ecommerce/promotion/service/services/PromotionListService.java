package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.models.PromotionList;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionListService {
    private final PromotionService promotionService;
    private final PromotionRepository promotionRepository;
    private PromotionList promotionList;

    public PromotionListService(PromotionService promotionService, PromotionRepository promotionRepository) {
        this.promotionService = promotionService;
        this.promotionRepository = promotionRepository;
        this.promotionList = (PromotionList) promotionRepository.findAll();
    }
}
