package com.microservices.ecommerce.promotion.service.repository;

import com.microservices.ecommerce.promotion.service.models.Promotion;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CouchbaseRepository<Promotion, String> {
    @Override
    void deleteById(String id);
}
