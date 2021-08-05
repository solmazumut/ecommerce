package com.microservices.ecommerce.promotion.service.eventModels;



import java.util.ArrayList;

public class Seller {
    private long sellerId;
    private ArrayList<Product> products;

    public Seller() {
    }

    public Seller(long sellerId, ArrayList<Product> products) {
        this.sellerId = sellerId;
        this.products = products;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", products=" + products +
                '}';
    }
}
