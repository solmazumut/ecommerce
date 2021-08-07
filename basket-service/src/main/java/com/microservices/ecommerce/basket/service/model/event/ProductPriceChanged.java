package com.microservices.ecommerce.basket.service.model.event;

public class ProductPriceChanged {

    private long productId;
    private long sellerId;
    private float price;

    public ProductPriceChanged() {
    }

    public ProductPriceChanged(long productId, long sellerId, float price) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
