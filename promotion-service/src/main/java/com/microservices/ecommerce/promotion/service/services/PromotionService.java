package com.microservices.ecommerce.promotion.service.services;

import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }
}
