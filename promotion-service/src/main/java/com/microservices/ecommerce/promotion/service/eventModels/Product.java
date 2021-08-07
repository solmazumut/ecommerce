package com.microservices.ecommerce.promotion.service.eventModels;



public class Product {
    private long productId;
    private String productTitle;
    private String productImageUrl;
    private int quantity;
    private float price;

    public Product() {
    }

    public Product(long productId, String productTitle, String productImageUrl, int quantity, float price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productImageUrl = productImageUrl;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }

    public float getTotalPrice() {
        return price * quantity;
    }
}
