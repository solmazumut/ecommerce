package com.microservices.ecommerce.basket.service.model.event.factory;

import com.microservices.ecommerce.basket.service.model.event.UserMadeAddBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeDeleteBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.UserMadeUpdateBasketOperation;
import com.microservices.ecommerce.basket.service.routes.request.models.AddProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.RemoveProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.SetProductQuantityRequestObject;

public class UserMadeBasketOperationFactrory {

    public static UserMadeAddBasketOperation createUserMadeAddBasketOperation(long productId, String productTitle,
                                                                              String productImageUrl, long sellerId,
                                                                              float price, int stock, long userId) {
        UserMadeAddBasketOperation userMadeAddBasketOperation = new UserMadeAddBasketOperation();
        userMadeAddBasketOperation.setProductId(productId);
        userMadeAddBasketOperation.setProductTitle(productTitle);
        userMadeAddBasketOperation.setProductImageUrl(productImageUrl);
        userMadeAddBasketOperation.setSellerId(sellerId);
        userMadeAddBasketOperation.setPrice(price);
        userMadeAddBasketOperation.setStock(stock);
        userMadeAddBasketOperation.setUserId(userId);

        return userMadeAddBasketOperation;
    }
    public static UserMadeAddBasketOperation createUserMadeAddBasketOperation(AddProductRequestObject addProductRequestObject) {
        UserMadeAddBasketOperation userMadeAddBasketOperation = new UserMadeAddBasketOperation();
        userMadeAddBasketOperation.setProductId(addProductRequestObject.getProductId());
        userMadeAddBasketOperation.setProductTitle(addProductRequestObject.getProductTitle());
        userMadeAddBasketOperation.setProductImageUrl(addProductRequestObject.getProductImageUrl());
        userMadeAddBasketOperation.setSellerId(addProductRequestObject.getSellerId());
        userMadeAddBasketOperation.setPrice(addProductRequestObject.getPrice());
        userMadeAddBasketOperation.setStock(addProductRequestObject.getStock());
        userMadeAddBasketOperation.setUserId(addProductRequestObject.getUserId());

        return userMadeAddBasketOperation;
    }

    public static UserMadeUpdateBasketOperation createUserMadeUpdateBasketOperation(long productId, String productTitle,
                                                                                    String productImageUrl, long sellerId,
                                                                                    float price, int stock, long userId,
                                                                                    int quantity) {
        UserMadeUpdateBasketOperation userMadeUpdateBasketOperation = new UserMadeUpdateBasketOperation();
        userMadeUpdateBasketOperation.setProductId(productId);
        userMadeUpdateBasketOperation.setProductTitle(productTitle);
        userMadeUpdateBasketOperation.setProductImageUrl(productImageUrl);
        userMadeUpdateBasketOperation.setSellerId(sellerId);
        userMadeUpdateBasketOperation.setPrice(price);
        userMadeUpdateBasketOperation.setStock(stock);
        userMadeUpdateBasketOperation.setUserId(userId);
        userMadeUpdateBasketOperation.setQuantity(quantity);

        return userMadeUpdateBasketOperation;
    }

    public static UserMadeUpdateBasketOperation createUserMadeUpdateBasketOperation(SetProductQuantityRequestObject setProductQuantityRequestObject) {
        UserMadeUpdateBasketOperation userMadeUpdateBasketOperation = new UserMadeUpdateBasketOperation();
        userMadeUpdateBasketOperation.setProductId(setProductQuantityRequestObject.getProductId());
        userMadeUpdateBasketOperation.setProductTitle(setProductQuantityRequestObject.getProductTitle());
        userMadeUpdateBasketOperation.setProductImageUrl(setProductQuantityRequestObject.getProductImageUrl());
        userMadeUpdateBasketOperation.setSellerId(setProductQuantityRequestObject.getSellerId());
        userMadeUpdateBasketOperation.setPrice(setProductQuantityRequestObject.getPrice());
        userMadeUpdateBasketOperation.setStock(setProductQuantityRequestObject.getStock());
        userMadeUpdateBasketOperation.setUserId(setProductQuantityRequestObject.getUserId());
        userMadeUpdateBasketOperation.setQuantity(setProductQuantityRequestObject.getQuantity());

        return userMadeUpdateBasketOperation;
    }

    public static UserMadeDeleteBasketOperation createUserMadeDeleteBasketOperation(long productId,  long sellerId,
                                                                                    long userId) {
        UserMadeDeleteBasketOperation userMadeDeleteBasketOperation = new UserMadeDeleteBasketOperation();
        userMadeDeleteBasketOperation.setProductId(productId);
        userMadeDeleteBasketOperation.setSellerId(sellerId);
        userMadeDeleteBasketOperation.setUserId(userId);

        return userMadeDeleteBasketOperation;
    }

    public static UserMadeDeleteBasketOperation createUserMadeDeleteBasketOperation(RemoveProductRequestObject removeProductRequestObject) {
        UserMadeDeleteBasketOperation userMadeDeleteBasketOperation = new UserMadeDeleteBasketOperation();
        userMadeDeleteBasketOperation.setProductId(removeProductRequestObject.getProductId());
        userMadeDeleteBasketOperation.setSellerId(removeProductRequestObject.getSellerId());
        userMadeDeleteBasketOperation.setUserId(removeProductRequestObject.getUserId());

        return userMadeDeleteBasketOperation;
    }
}
