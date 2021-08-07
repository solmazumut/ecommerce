package com.microservices.ecommerce.basket.service.repository;

import com.microservices.ecommerce.basket.service.model.Product;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends CouchbaseRepository<Product, String>{
    @Override
    void deleteById(String id);
}

