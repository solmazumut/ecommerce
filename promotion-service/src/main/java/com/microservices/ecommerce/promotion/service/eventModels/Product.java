package com.microservices.ecommerce.promotion.service.eventModels;



public class Product {
    private long productId;
    private String productTitle;
    private String productImageUrl;
    private int stock;
    private float price;

    public Product() {
    }

    public Product(long productId, String productTitle, String productImageUrl, int stock, float price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImageUrl = productImageUrl;
        this.stock = stock;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
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

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productTitle='" + productTitle + '\'' +
                ", productImageUrl='" + productImageUrl + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
