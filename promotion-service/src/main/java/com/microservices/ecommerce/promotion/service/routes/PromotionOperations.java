package com.microservices.ecommerce.promotion.service.routes;

import com.microservices.ecommerce.promotion.service.factory.PromotionFactory;
import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.repository.PromotionRepository;
import com.microservices.ecommerce.promotion.service.routes.request.models.PromotionRequestObject;
import com.microservices.ecommerce.promotion.service.routes.request.models.RequestWithIdAndList;
import com.microservices.ecommerce.promotion.service.services.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/promotions")
public class PromotionOperations {

    private final PromotionRepository promotionRepository;
    private final PromotionService promotionService;

    public PromotionOperations(PromotionRepository promotionRepository, PromotionService promotionService) {
        this.promotionRepository = promotionRepository;
        this.promotionService = promotionService;
    }

    @PutMapping(path = "/add-promotion")
    public ResponseEntity<String> addPromotion(@RequestBody PromotionRequestObject promotionRequestObject) {
        Promotion promotion = PromotionFactory.createPromotionFromRequestObject(promotionRequestObject);
        String message = promotionService.create(promotion);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> allPromotions = promotionRepository.findAll();

        return new ResponseEntity<List<Promotion>>(allPromotions, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable Long id) {
        String promotionId = String.valueOf(id);
        Promotion promotion;
        try {
            promotion = promotionService.findById(promotionId);
            return new ResponseEntity<Promotion>(promotion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Promotion>(new Promotion(), HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping(path = "/{id}")
    public void deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
    }

    @PostMapping(path = "/update-promotion")
    public ResponseEntity<String> updatePromotion(@RequestBody PromotionRequestObject promotionRequestObject) {
        Promotion promotion = PromotionFactory.createPromotionFromRequestObject(promotionRequestObject);
        String message = promotionService.update(promotion);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }


    @PostMapping(path = "/add-products")
    public ResponseEntity<String> addProducts(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.addProducts(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/add-sellers")
    public ResponseEntity<String> addSellers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.addSellers(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PostMapping(path = "/add-users")
    public ResponseEntity<String> addUsers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.addWhichUsers(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-products")
    public ResponseEntity<String> deleteProducts(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.deleteProducts(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-sellers")
    public ResponseEntity<String> deleteSellers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.deleteSellers(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-users")
    public ResponseEntity<String> deleteUsers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        long id = requestWithIdAndList.getPromotionId();
        ArrayList<Long> idList = requestWithIdAndList.getIdList();
        String message = promotionService.deleteWhichUsers(id, idList);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
