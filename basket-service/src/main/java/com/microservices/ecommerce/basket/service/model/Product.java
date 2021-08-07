package com.microservices.ecommerce.basket.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;
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
    private ArrayList<Seller> sellers;

    public Product() {

    }

    public Product(long productId, String imageUrl, String title, ArrayList<Seller> sellers) {
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

    public ArrayList<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
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
            int numberOfUserWhoAddedSeller = seller.getUsers().size();
            if(numberOfUserWhoAddedSeller < 1) {
                isSuccessful = deleteSeller(sellerId);
            }
        }
        return isSuccessful;
    }

    private boolean deleteSeller(long sellerId) {
        boolean isSuccessful = false;
        int indexNotFound = -1;
        int sellerIndex = getSellerIndex(sellerId);
        if(sellerIndex != indexNotFound) {
            sellers.remove(sellerIndex);
            isSuccessful = true;
        }
        return isSuccessful;
    }


    public void addSeller(Seller newSeller) {
        int indexNotFound = -1;
        int sellerIndex = getSellerIndex(newSeller.getSellerId());
        if(sellerIndex == indexNotFound) {
            sellers.add(newSeller);
        } else {
            Seller oldSeller = getSellers().get(sellerIndex);
            ArrayList<User> newUserList = newSeller.getUsers();
            for (User newUser: newUserList) {
                long userId = newUser.getUserId();
                oldSeller.addOrUpdateUserOneQuantity(userId);
            }
            sellers.set(sellerIndex, oldSeller);
        }
    }

    public void addAndSetSeller(Seller newSeller) {
        int indexNotFound = -1;
        int sellerIndex = getSellerIndex(newSeller.getSellerId());
        if(sellerIndex == indexNotFound) {
            sellers.add(newSeller);
        } else {
            Seller oldSeller = getSellers().get(sellerIndex);
            ArrayList<User> newUserList = newSeller.getUsers();
            for (User newUser: newUserList) {
                oldSeller.addOrUpdateUser(newUser);
            }
            sellers.set(sellerIndex, oldSeller);
        }
    }


    public Seller getSellerWithId(long sellerId) {
        Seller seller = null;
        for (Seller sellerSellingProduct: this.sellers) {
            long sellerIdSellingProduct = sellerSellingProduct.getSellerId();
            if(sellerIdSellingProduct == sellerId) {
                seller = sellerSellingProduct;
                break;
            }
        }
        return seller;
    }

    public boolean isAnyUserAddedProduct() {
        boolean result = false;
        for (Seller seller: this.sellers) {
            if (seller.isAnyUserAddedSeller()) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void updateSeller(Seller seller) {
        int indexNotFound = -1;
        int indexStackOverFlow = sellers.size();
        int index = getSellerIndex(seller.getSellerId());
        if(indexNotFound < index && index < indexStackOverFlow) {
            sellers.set(index, seller);
        }
    }

    public void deleteSellerWithId(long sellerId) {
        int indexNotFound = -1;
        int indexStackOverFlow = sellers.size();
        int index = getSellerIndex(sellerId);
        if(indexNotFound < index && index < indexStackOverFlow) {
            sellers.remove(index);
        }
    }
}
