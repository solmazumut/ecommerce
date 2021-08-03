package com.microservices.ecommerce.basket.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;
@Document
public class Product {
    @Id
    private long productId;
    @Field
    private String imageUrl;
    @Field
    String title;
    @Field
    private List<Seller> sellers;

    public Product() {

    }

    public Product(long productId, String imageUrl, String title, List<Seller> sellers) {
        this.productId = productId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.sellers = sellers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    private int getSellerIndex(long sellerId) {
        int index = -1;
        for (int i = 0; i < sellers.size(); i++) {
            if(sellers.get(i).getSellerId() == sellerId) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addOrUpdateUser(Seller seller, User user) {
        int indexNotFound = -1;
        int sellerIndex = getSellerIndex(seller.getSellerId());
        if(sellerIndex == indexNotFound) {
            seller.addOrUpdateUser(user);
            sellers.add(seller);
        } else {
            Seller oldSeller = sellers.get(sellerIndex);
            oldSeller.addOrUpdateUser(user);
            sellers.set(sellerIndex, oldSeller);
        }
    }

    public boolean deleteUserFromSeller(long sellerId, long userId) {
        boolean isSuccessful = false;
        int indexNotFound = -1;
        int sellerIndex = getSellerIndex(sellerId);
        if(sellerIndex != indexNotFound) {
            Seller seller = sellers.get(sellerIndex);
            isSuccessful = seller.deleteUser(userId);
            sellers.set(sellerIndex, seller);
        }
        return isSuccessful;
    }
}
