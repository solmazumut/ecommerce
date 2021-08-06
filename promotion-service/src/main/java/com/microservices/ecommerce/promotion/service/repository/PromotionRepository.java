package com.microservices.ecommerce.promotion.service.repository;

import com.microservices.ecommerce.promotion.service.models.Promotion;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends CouchbaseRepository<Promotion, String> {
    @Override
    void deleteById(String id);

    @Query("#{#n1ql.selectEntity} WHERE isEnable = true")
    List<Promotion> findAllByIsEnable();


}
