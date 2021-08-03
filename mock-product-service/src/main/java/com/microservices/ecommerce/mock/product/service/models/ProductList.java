package com.microservices.ecommerce.mock.product.service.models;

import java.util.List;

public class ProductList {
    private List<Product> products;

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
