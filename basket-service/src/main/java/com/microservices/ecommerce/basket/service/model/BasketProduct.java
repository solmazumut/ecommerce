package com.microservices.ecommerce.basket.service.model;

public class BasketProduct {
    private long prodcutId;
    private long sellerId;
    private String sellerName;
    private String productName;
    private String sizeOfProduct;
    private float price;
    private int stock;
    private int estimatedDeliveryTime;

    public BasketProduct() {
        this.prodcutId = -1;
        this.sellerId = -1;
        this.sellerName = "null";
        this.productName = "null";
        this.sizeOfProduct = "null";
        this.price = -1;
        this.stock = -1;
        this.estimatedDeliveryTime = -1;
    }

    boolean IsObjectConstructedCorrectly() {
        boolean correctConstructed = true;
        if( getProdcutId() == -1) {
            correctConstructed = false;
        }
        else if( getSellerId() == -1) {
            correctConstructed = false;
        }
        else if( getSellerName().equals("null")) {
            correctConstructed = false;
        }
        else if( getProductName().equals("null")) {
            correctConstructed = false;
        }
        else if( getSizeOfProduct().equals("null")) {
            correctConstructed = false;
        }
        else if( getPrice() == -1) {
            correctConstructed = false;
        }
        else if( getStock() == -1) {
            correctConstructed = false;
        }
        else if( getEstimatedDeliveryTime() == -1) {
            correctConstructed = false;
        }
        return  correctConstructed;
    }

    public long getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(long prodcutId) {
        this.prodcutId = prodcutId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSizeOfProduct() {
        return sizeOfProduct;
    }

    public void setSizeOfProduct(String sizeOfProduct) {
        this.sizeOfProduct = sizeOfProduct;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(int estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }
}
