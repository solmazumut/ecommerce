package com.microservices.ecommerce.mock.product.service.models;

public class Seller {
    private long id;
    private String name;
    private int stock;
    private float price;
    private float rate;
    private int estimatedDeliveryTime;

    public Seller(long id, String name, int stock, float price, float rate, int estimatedDeliveryTime) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.rate = rate;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
    public Seller setPriceAndStock(float price, int stock) {
        this.price = price;
        this.stock = stock;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
