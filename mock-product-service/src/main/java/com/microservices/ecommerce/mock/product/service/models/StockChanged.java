package com.microservices.ecommerce.mock.product.service.models;

public class StockChanged {
    private long productId;
    private long sellerId;
    private int stock;

    public StockChanged() {
    }

    public StockChanged(long productId, long sellerId, int stock) {
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
