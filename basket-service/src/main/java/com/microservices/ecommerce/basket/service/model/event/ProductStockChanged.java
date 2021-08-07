package com.microservices.ecommerce.basket.service.model.event;

public class ProductStockChanged {
    private long productId;
    private long sellerId;
    private int stock;

    public ProductStockChanged() {
    }

    public ProductStockChanged(long productId, long sellerId, int stock) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
