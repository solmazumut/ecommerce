package com.microservices.ecommerce.basket.service.service;

import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.Seller;
import com.microservices.ecommerce.basket.service.model.User;
import com.microservices.ecommerce.basket.service.model.event.NotificationEvents;
import com.microservices.ecommerce.basket.service.model.event.ProductPriceChanged;
import com.microservices.ecommerce.basket.service.model.event.UserMadeBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.impl.OutOfStockNotification;
import com.microservices.ecommerce.basket.service.model.event.impl.ProductRemovedFromBasketNotification;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeAddBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeUpdateBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.factory.UserMadeBasketOperationFactrory;
import com.microservices.ecommerce.basket.service.repository.ProductRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    private Product addPropertyToExistProduct(Product oldProduct, Product newProduct) {
        List<Seller> newSellers = newProduct.getSellers();
        for (Seller newSeller : newSellers) {
            oldProduct.addSeller(newSeller);
        }
        return productRepository.save(oldProduct);
    }

    private Product setPropertyToExistProduct(Product oldProduct, Product newProduct) {
        List<Seller> newSellers = newProduct.getSellers();
        for (Seller newSeller : newSellers) {
            oldProduct.addAndSetSeller(newSeller);
        }
        return productRepository.save(oldProduct);
    }

    public Product createOrAddPropertyToExistProduct(Product product) {
        Product finalProduct;
        try {
            Product oldProduct = this.findById(String.valueOf(product.getProductId()));
            finalProduct = addPropertyToExistProduct(oldProduct, product);
        } catch (Exception e) {
            finalProduct = create(product);
        }

        return finalProduct;
    }

    public Product createOrSetPropertyToExistProduct(Product product) {
        Product finalProduct;
        try {
            Product oldProduct = this.findById(String.valueOf(product.getProductId()));
            finalProduct = setPropertyToExistProduct(oldProduct, product);
        } catch (Exception e) {
            finalProduct = create(product);
        }

        return finalProduct;
    }



    public Product findById(String id) {
        return productRepository.findById(id).orElseThrow( ()-> new RuntimeException(
                String.format("Product not found")
        ));
    }


    public void deleteUser(long productId, long sellerId, long userId) {
        Product product = this.findById(String.valueOf(productId));
        product.deleteUserFromSeller(sellerId, userId);
        boolean isThereAnyUserAddedProduct = product.isAnyUserAddedProduct();
        if (isThereAnyUserAddedProduct) {
            productRepository.save(product);
        } else {
            String id = String.valueOf(productId);
            productRepository.deleteById(id);
        }
    }

    public Pair<ArrayList<UserMadeUpdateBasketOperation>, Pair<String, Float>>
    getProductFollowerBasketEventsForPriceChangeAndUpdateDB(ProductPriceChanged productPriceChanged) {
        Pair<ArrayList<UserMadeUpdateBasketOperation>, Pair<String, Float>> respond = null;
        Float oldPrice = null;
        ArrayList<UserMadeUpdateBasketOperation>
                events = new ArrayList<UserMadeUpdateBasketOperation>();
        Product product = null;
        Seller seller = null;
        try {
            String productId = String.valueOf(productPriceChanged.getProductId());
            product = findById(productId);
            seller = product.getSellerWithId(productPriceChanged.getSellerId());
            oldPrice = seller.getPrice();
            if(seller == null) {
                throw new RuntimeException("Seller not found");
            }
        } catch (Exception e) {
            events = null;
        }
        if(events != null) {
            Float newPrice = productPriceChanged.getPrice();
            updateProductPrice(product, seller, newPrice);

            events = getOccuredEvents(events, product, seller);

            respond = Pair.of(events, Pair.of(product.getTitle(), oldPrice));
        }
        return respond;
    }

    private void updateProductPrice(Product product, Seller seller, Float newPrice) {
        seller.setPrice(newPrice);
        product.updateSeller(seller);
        this.productRepository.save(product);
    }

    private ArrayList<UserMadeUpdateBasketOperation>
        getOccuredEvents(ArrayList<UserMadeUpdateBasketOperation> events, Product product, Seller seller) {

        ArrayList<User> users = seller.getUsers();
        for (User user : users) {
            UserMadeUpdateBasketOperation
                    newEvent = UserMadeBasketOperationFactrory
                    .createUserMadeUpdateBasketOperation(
                            product.getProductId(),
                            product.getTitle(),
                            product.getImageUrl(),
                            seller.getSellerId(),
                            seller.getPrice(),
                            seller.getStock(),
                            user.getUserId(),
                            user.getQuantity()
                    );
            events.add(newEvent);
        }
        return events;
    }


    public Pair<Boolean, Product> updateProductStock(long productId, long sellerId, int stock) {
        Pair<Boolean, Product> respond = null;
        boolean isUpdated = false;
        Product product = null;
        if(stock > 0) {
            try {
                String id = String.valueOf(productId);
                product = findById(id);
                Seller seller = product.getSellerWithId(sellerId);
                seller.setStock(stock);
                this.productRepository.save(product);
                isUpdated = true;
            } catch (Exception e) {
                product = null;
            }
        }
        respond = Pair.of(isUpdated, product);
        return respond;
    }

    public Pair<NotificationEvents, ArrayList<UserMadeBasketOperation>>
                checkStockAndUpdateDbAndReturnNotificationAndDbUpdateEvents(long productId, long sellerId) {
        Product product = findById(String.valueOf(productId));
        Seller seller = product.getSellerWithId(sellerId);
        ArrayList<UserMadeBasketOperation> basketOperations = new ArrayList<UserMadeBasketOperation>();
        ArrayList<Long> userIdListForNotification = new ArrayList<Long>();
        int stock = seller.getStock();

        ArrayList<User> newUsers = new ArrayList<User>();
        ArrayList<User> users = seller.getUsers();
        for (User user: users) {
            if(user.getQuantity() > stock) {
                user.setQuantity(stock);

                UserMadeUpdateBasketOperation basketOperation = UserMadeBasketOperationFactrory
                        .createUserMadeUpdateBasketOperation(productId, product.getTitle(), product.getImageUrl(),
                                sellerId, seller.getPrice(), stock, user.getUserId(), user.getQuantity());
                basketOperations.add(basketOperation);

                userIdListForNotification.add(user.getUserId());
            }
            if(stock != 0) {
                newUsers.add(user);
            }
        }
        int noUser = 0;
        if(newUsers.size() == noUser) {
            product.deleteSellerWithId(sellerId);
        } else {
            seller.setUsers(newUsers);
            product.updateSeller(seller);
        }
        this.productRepository.save(product);

        NotificationEvents notificationEvents;

        int outOfStock = 0;
        if(stock == outOfStock) {
            notificationEvents = new OutOfStockNotification(productId, product.getTitle());
        } else {
            notificationEvents = new ProductRemovedFromBasketNotification(productId, product.getTitle());
        }
        Pair<NotificationEvents, ArrayList<UserMadeBasketOperation>> result;
        if(userIdListForNotification.size() == noUser) {
            result = null;
        } else {
            notificationEvents.setUserIdList(userIdListForNotification);
        }

        result = Pair.of(notificationEvents, basketOperations);
        return result;
    }
}
