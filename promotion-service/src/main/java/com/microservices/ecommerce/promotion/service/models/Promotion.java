package com.microservices.ecommerce.promotion.service.models;

import com.microservices.ecommerce.promotion.service.eventModels.Basket;
import com.microservices.ecommerce.promotion.service.eventModels.Product;
import com.microservices.ecommerce.promotion.service.eventModels.Seller;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.ArrayList;

@Document
public class Promotion {
    @Id
    long promotionId;
    @Field
    String promotionTitle;
    @Field
    boolean isEnable;
    @Field
    float discount;
    @Field
    float totalPriceMax;
    @Field
    float totalPriceMin;
    @Field
    float sellerTotalPriceMax;
    @Field
    float sellerTotalPriceMin;
    @Field
    boolean isItEnoughOneSellerForPromotion;
    @Field
    ArrayList<Long> whichUsers;
    @Field
    ArrayList<Long> whichSellers;
    @Field
    ArrayList<Long> whichProducts;
    @Field
    ArrayList<Long> users;
    @Field
    private boolean isExistAllPricePromotion;
    @Field
    private boolean isExistSellerPromotion;
    @Field
    private boolean isExistProductPromotion;
    @Field
    private boolean isExistUserPromotion;

    public Promotion() {
        isExistAllPricePromotion = false;
        isExistSellerPromotion = false;
        isExistProductPromotion = false;
        isExistUserPromotion = false;

    }

    public Promotion(long promotionId, String promotionTitle, boolean isEnable,
                     float discount, float totalPriceMax, float totalPriceMin,
                     float sellerTotalPriceMax, float sellerTotalPriceMin,
                     boolean isItEnoughOneSellerForPromotion, ArrayList<Long> whichUsers,
                     ArrayList<Long> whichSellers, ArrayList<Long> whichProducts, ArrayList<Long> users) {
        this.promotionId = promotionId;
        this.promotionTitle = promotionTitle;
        this.isEnable = isEnable;
        this.discount = discount;
        this.totalPriceMax = totalPriceMax;
        this.totalPriceMin = totalPriceMin;
        this.sellerTotalPriceMax = sellerTotalPriceMax;
        this.sellerTotalPriceMin = sellerTotalPriceMin;
        this.isItEnoughOneSellerForPromotion = isItEnoughOneSellerForPromotion;
        this.whichUsers = whichUsers;
        this.whichSellers = whichSellers;
        this.whichProducts = whichProducts;
        this.users = users;

        computeThereIsSellerPromotion();
        computeThereIsAllPricePromotion();
        computeThereIsProductPromotion();
        computeThereIsUserPromotion();
    }

    private void computeThereIsAllPricePromotion() {
        if(totalPriceMax < 0 && totalPriceMin < 0) {
            isExistAllPricePromotion = false;
        } else {
            isExistAllPricePromotion = true;
        }
    }

    private void computeThereIsSellerPromotion() {
        if(sellerTotalPriceMax < 0 && sellerTotalPriceMin < 0) {
            if(whichSellers == null) {
                isExistSellerPromotion = false;
            } else if(whichSellers.size() < 1) {
                isExistSellerPromotion = false;
            } else {
                isExistSellerPromotion = true;
            }
        } else {
            isExistSellerPromotion = true;
        }
    }

    private void computeThereIsProductPromotion() {
        if(whichProducts == null) {
            isExistProductPromotion = false;
        } else if(whichProducts.size() < 1) {
            isExistProductPromotion = false;
        } else {
            isExistProductPromotion = true;
        }
    }

    private void computeThereIsUserPromotion() {
        if(whichUsers == null) {
            isExistUserPromotion = false;
        } else if(whichUsers.size() < 1) {
            isExistUserPromotion = false;
        } else {
            isExistUserPromotion = true;
        }
    }



    public long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(long promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTotalPriceMax() {
        return totalPriceMax;
    }

    public void setTotalPriceMax(float totalPriceMax) {
        this.totalPriceMax = totalPriceMax;
        computeThereIsAllPricePromotion();
    }

    public float getTotalPriceMin() {
        return totalPriceMin;
    }

    public void setTotalPriceMin(float totalPriceMin) {
        this.totalPriceMin = totalPriceMin;
        computeThereIsAllPricePromotion();
    }

    public float getSellerTotalPriceMax() {
        return sellerTotalPriceMax;
    }

    public void setSellerTotalPriceMax(float sellerTotalPriceMax) {
        this.sellerTotalPriceMax = sellerTotalPriceMax;
        computeThereIsSellerPromotion();
    }

    public float getSellerTotalPriceMin() {
        return sellerTotalPriceMin;
    }

    public void setSellerTotalPriceMin(float sellerTotalPriceMin) {
        this.sellerTotalPriceMin = sellerTotalPriceMin;
        computeThereIsSellerPromotion();
    }

    public boolean isItEnoughOneSellerForPromotion() {
        return isItEnoughOneSellerForPromotion;
    }

    public void setItEnoughOneSellerForPromotion(boolean itEnoughOneSellerForPromotion) {
        isItEnoughOneSellerForPromotion = itEnoughOneSellerForPromotion;
    }

    public ArrayList<Long> getWhichUsers() {
        return whichUsers;
    }

    public void setWhichUsers(ArrayList<Long> whichUsers) {
        this.whichUsers = whichUsers;
        computeThereIsUserPromotion();
    }

    public ArrayList<Long> getWhichSellers() {
        return whichSellers;
    }

    public void setWhichSellers(ArrayList<Long> whichSellers) {
        this.whichSellers = whichSellers;
        computeThereIsSellerPromotion();
    }

    public ArrayList<Long> getWhichProducts() {
        return whichProducts;
    }

    public void setWhichProducts(ArrayList<Long> whichProducts) {
        this.whichProducts = whichProducts;

        computeThereIsProductPromotion();
    }

    public ArrayList<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
        computeThereIsUserPromotion();
    }

    public void update(Promotion promotion) {
        String promotionTitle = promotion.getPromotionTitle();
        boolean isEnable = promotion.isEnable();
        float discount = promotion.getDiscount();
        float totalPriceMax = promotion.getTotalPriceMax();
        float totalPriceMin = promotion.getTotalPriceMin();
        float sellerTotalPriceMax = promotion.getSellerTotalPriceMax();
        float sellerTotalPriceMin = promotion.getSellerTotalPriceMin();
        boolean isItEnoughOneSellerForPromotion = promotion.isItEnoughOneSellerForPromotion();
        ArrayList<Long> whichUsers = promotion.getWhichUsers();
        ArrayList<Long> whichSellers = promotion.getWhichSellers();
        ArrayList<Long> whichProducts = promotion.getWhichProducts();

        setPromotionTitle(promotionTitle);
        setEnable(isEnable);
        setDiscount(discount);
        setTotalPriceMax(totalPriceMax);
        setTotalPriceMin(totalPriceMin);
        setSellerTotalPriceMax(sellerTotalPriceMax);
        setSellerTotalPriceMin(sellerTotalPriceMin);
        setItEnoughOneSellerForPromotion(isItEnoughOneSellerForPromotion);
        setWhichUsers(whichUsers);
        setWhichSellers(whichSellers);
        setWhichProducts(whichProducts);

        computeThereIsSellerPromotion();
        computeThereIsAllPricePromotion();
        computeThereIsProductPromotion();
        computeThereIsUserPromotion();
    }

    public void addProducts(ArrayList<Long> products) {
        for (Long productId: products) {
            boolean isProductExist = this.whichProducts.contains(productId);
            if(!isProductExist) {
                this.whichProducts.add(productId);
            }
        }
        computeThereIsProductPromotion();
    }

    public void deleteProducts(ArrayList<Long> products) {
        for (Long productId: products) {
            boolean isProductExist = this.whichProducts.contains(productId);
            if(isProductExist) {
                this.whichProducts.remove(productId);
            }
        }

        computeThereIsProductPromotion();
    }

    public void addSellers(ArrayList<Long> sellers) {
        for (Long sellerId: sellers) {
            boolean isSellerExist = this.whichSellers.contains(sellerId);
            if(!isSellerExist) {
                this.whichSellers.add(sellerId);
            }
        }
        computeThereIsSellerPromotion();
    }

    public void deleteSellers(ArrayList<Long> sellers) {
        for (Long sellerId: sellers) {
            boolean isSellerExist = this.whichSellers.contains(sellerId);
            if(isSellerExist) {
                this.whichSellers.remove(sellerId);
            }
        }
        computeThereIsSellerPromotion();
    }

    public void addWhichUsers(ArrayList<Long> users) {
        for (Long userId: users) {
            boolean isUserExist = this.whichUsers.contains(userId);
            if(!isUserExist) {
                this.whichUsers.add(userId);
            }
        }
        computeThereIsUserPromotion();
    }

    public void deleteWhichUsers(ArrayList<Long> users) {
        for (Long userId: users) {
            boolean isUserExist = this.whichUsers.contains(userId);
            if(isUserExist) {
                this.whichUsers.remove(userId);
            }
        }
        computeThereIsUserPromotion();
    }

    public void addUser(Long userId) {
        if(this.users != null) {
            boolean isUserExist = this.users.contains(userId);
            if(!isUserExist) {

                this.users.add(userId);
            }
        } else {
            this.users = new ArrayList<Long>();
            this.users.add(userId);
        }

    }

    public void deleteUser(Long userId) {
        if(this.users != null) {
            boolean isUserExist = this.users.contains(userId);
            if (isUserExist) {
                this.users.remove(userId);
            }
        }
    }


    private float calculateDiscount(float price) {
        float calculatedDiscount;
        if(this.discount < 0) {
            calculatedDiscount = 0;
        } else if(this.discount < 1) {
            calculatedDiscount = price * this.discount;
        } else {
            calculatedDiscount = this.discount;
        }
        return calculatedDiscount;
    }

    private boolean checkIdInListOrListEmpty(long id, ArrayList<Long> list) {
        Long newId = id;
        boolean isItSuitableForPromotion = false;
        if(list == null) {
            isItSuitableForPromotion = true;
        } else if(list.size() < 1) {
            isItSuitableForPromotion = true;
        } else {
            isItSuitableForPromotion = list.contains(newId);
        }
        return isItSuitableForPromotion;
    }

    private boolean checkMinPriceIsSuitable(float price, float minPrice) {
        boolean result = false;
        if(minPrice < 0) {
            result = true;
        } else {
            if(price >= minPrice) {
                result = true;
            }
        }
        return result;
    }

    private boolean checkMaxPriceIsSuitable(float price, float maxPrice) {
        boolean result = false;
        if(maxPrice < 0) {
            result = true;
        } else {
            if(price <= maxPrice) {
                result = true;
            }
        }
        return result;
    }
    public com.microservices.ecommerce.promotion.service.eventModels.Promotion getPromotionForBasket(Basket basket) {
        boolean isItSuitableForPromotion = true;

        com.microservices.ecommerce.promotion.service.eventModels.Promotion eventPromotion = null;

        isItSuitableForPromotion = checkSellerPromotion(basket, isItSuitableForPromotion);

        isItSuitableForPromotion = checkAllPricePromotion(basket, isItSuitableForPromotion);

        isItSuitableForPromotion = checkProductPromotion(basket, isItSuitableForPromotion);

        isItSuitableForPromotion = checkUserPromotion(basket, isItSuitableForPromotion);

        if(isItSuitableForPromotion) {
            long promotionId = this.promotionId;
            String title = this.promotionTitle;
            float discountPrice = calculateDiscount(basket.getTotalPrice());

            eventPromotion = new com.microservices.ecommerce.promotion.service.eventModels.Promotion(promotionId, title, discountPrice);
        }

    return eventPromotion;
    }

    private boolean checkUserPromotion(Basket basket, boolean isItSuitableForPromotion) {
        if (isExistUserPromotion && isItSuitableForPromotion) {
            isItSuitableForPromotion = checkIdInListOrListEmpty(basket.getUserId(), whichUsers);
        }
        return isItSuitableForPromotion;
    }

    private boolean checkProductPromotion(Basket basket, boolean isItSuitableForPromotion) {
        if (isExistProductPromotion && isItSuitableForPromotion) {
            isItSuitableForPromotion = false;
            ArrayList<Product> productList = basket.getAllProducts();
            for (Product product : productList) {
                boolean isProductSuitable = checkIdInListOrListEmpty(product.getProductId(), this.whichProducts);

                if(isProductSuitable) {
                    isItSuitableForPromotion = true;
                    break;
                }
            }
        }
        return isItSuitableForPromotion;
    }

    private boolean checkAllPricePromotion(Basket basket, boolean isItSuitableForPromotion) {
        if (isExistAllPricePromotion && isItSuitableForPromotion) {

            isItSuitableForPromotion = checkMinPriceIsSuitable(basket.getTotalPrice() , this.totalPriceMin);

            if(isItSuitableForPromotion) {
                isItSuitableForPromotion = checkMaxPriceIsSuitable(basket.getTotalPrice() , this.totalPriceMax);
            }
        }
        return isItSuitableForPromotion;
    }

    private boolean checkSellerPromotion(Basket basket, boolean isItSuitableForPromotion) {
        if (isExistSellerPromotion && isItSuitableForPromotion) {
            ArrayList<Seller> sellerList = basket.getSellers();
            if(isItEnoughOneSellerForPromotion) {
                isItSuitableForPromotion = false;
                for (Seller seller : sellerList) {
                        boolean isSellerSuitable = checkIdInListOrListEmpty(seller.getSellerId(), this.whichSellers);
                        if(isSellerSuitable) {
                            isSellerSuitable = checkMinPriceIsSuitable(seller.getTotalPrice() , this.sellerTotalPriceMin);
                        }
                        if(isSellerSuitable) {
                            isSellerSuitable = checkMaxPriceIsSuitable(seller.getTotalPrice() , this.sellerTotalPriceMax);
                        }
                        if(isSellerSuitable) {
                            isItSuitableForPromotion = true;
                            break;
                        }
                }
            } else {
                for (Seller seller : sellerList) {
                    boolean isSellerSuitable = checkIdInListOrListEmpty(seller.getSellerId(), this.whichSellers);
                    if(isSellerSuitable) {
                        isSellerSuitable = checkMinPriceIsSuitable(seller.getTotalPrice() , this.sellerTotalPriceMin);
                    }
                    if(isSellerSuitable) {
                        isSellerSuitable = checkMaxPriceIsSuitable(seller.getTotalPrice() , this.sellerTotalPriceMax);
                    }
                    if(!isSellerSuitable) {
                        isItSuitableForPromotion = false;
                        break;
                    }
                }
            }
        }
        return  isItSuitableForPromotion;
    }

    public boolean isExistUser(long id) {
        boolean result = false;
        for (Long userId: this.users) {
            if (userId == id) {
                result = true;
                break;
            }
        }
        return result;
    }
}
