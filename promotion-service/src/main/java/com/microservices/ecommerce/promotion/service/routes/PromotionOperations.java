package com.microservices.ecommerce.promotion.service.routes;

import com.microservices.ecommerce.promotion.service.models.Promotion;
import com.microservices.ecommerce.promotion.service.routes.request.models.PromotionRequestObject;
import com.microservices.ecommerce.promotion.service.routes.request.models.RequestWithIdAndList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/promotions")
public class PromotionOperations {

    @PutMapping(path = "/add-promotion")
    public ResponseEntity<String> addPromotion(@RequestBody PromotionRequestObject promotionRequestObject) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<List<Promotion>>(List.of(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable Long id) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<Promotion>(new Promotion(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Promotion> deletePromotion(@PathVariable Long id) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<Promotion>(new Promotion(), HttpStatus.OK);
    }

    @PostMapping(path = "/update-promotion")
    public ResponseEntity<String> updatePromotion(@RequestBody PromotionRequestObject promotionRequestObject) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @PostMapping(path = "/add-products")
    public ResponseEntity<String> addProducts(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @PostMapping(path = "/add-sellers")
    public ResponseEntity<String> addSellers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @PostMapping(path = "/add-users")
    public ResponseEntity<String> addUsers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-products")
    public ResponseEntity<String> deleteProducts(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-sellers")
    public ResponseEntity<String> deleteSellers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete-products")
    public ResponseEntity<String> deleteUsers(@RequestBody RequestWithIdAndList requestWithIdAndList) {
        ResponseEntity<String> responseEntity;


        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }
}
