package com.microservices.ecommerce.mock.product.service.models;

import java.util.List;

public class Product {

    private long productId;
    private String productName;
    private List<Seller> sellers;
    private List<String> sizeOfProduct;

    public Product(long productId, String productName, List<Seller> sellers, List<String> sizeOfProduct) {
        this.productId = productId;
        this.productName = productName;
        this.sellers = sellers;
        this.sizeOfProduct = sizeOfProduct;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public List<String> getSizeOfProduct() {
        return sizeOfProduct;
    }

    public void setSizeOfProduct(List<String> sizeOfProduct) {
        this.sizeOfProduct = sizeOfProduct;
    }
}
