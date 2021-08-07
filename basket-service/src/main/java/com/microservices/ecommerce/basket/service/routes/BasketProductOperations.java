package com.microservices.ecommerce.basket.service.routes;

import com.microservices.ecommerce.basket.service.factory.ProductFactory;
import com.microservices.ecommerce.basket.service.model.Product;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeAddBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeDeleteBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.impl.UserMadeUpdateBasketOperation;
import com.microservices.ecommerce.basket.service.model.event.factory.UserMadeBasketOperationFactrory;
import com.microservices.ecommerce.basket.service.routes.request.models.AddProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.RemoveProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.SetProductQuantityRequestObject;
import com.microservices.ecommerce.basket.service.service.KafkaService;
import com.microservices.ecommerce.basket.service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/basket-product")
public class BasketProductOperations {

    private final ProductService productService;
    private final KafkaService kafkaService;

    public BasketProductOperations(ProductService productService, KafkaService kafkaService) {
        this.productService = productService;
        this.kafkaService = kafkaService;
    }

    @PutMapping(path = "/add-product")
    public ResponseEntity<String> addProductToBasket(@RequestBody AddProductRequestObject addProductRequestObject) {
        ResponseEntity<String> responseEntity;

        Product product = ProductFactory.createProductWithRequestObject(addProductRequestObject);

        productService.createOrAddPropertyToExistProduct(product);

        UserMadeAddBasketOperation userMadeAddBasketOperation = UserMadeBasketOperationFactrory
                .createUserMadeAddBasketOperation(addProductRequestObject);

        kafkaService.sendUserMadeBasketOperationMessage(userMadeAddBasketOperation);

        return new ResponseEntity<String>("Product is added", HttpStatus.OK);
    }

    @PostMapping(path = "/set-product-quantity")
    public ResponseEntity<String> setProductQuantity(@RequestBody SetProductQuantityRequestObject setProductQuantityRequestObject) {
        ResponseEntity<String> responseEntity;


        Product product = ProductFactory.createProductWithRequestObject(setProductQuantityRequestObject);

        productService.createOrSetPropertyToExistProduct(product);

        UserMadeUpdateBasketOperation userMadeUpdateBasketOperation = UserMadeBasketOperationFactrory
                .createUserMadeUpdateBasketOperation(setProductQuantityRequestObject);

        kafkaService.sendUserMadeBasketOperationMessage(userMadeUpdateBasketOperation);

        return new ResponseEntity<String>("Product is uptaded", HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove-product")
    public  ResponseEntity<String> removeProduct(@RequestBody RemoveProductRequestObject removeProductRequestObject) {
        ResponseEntity<String> responseEntity;
        long productId = removeProductRequestObject.getProductId();
        long sellerId = removeProductRequestObject.getSellerId();
        long userId = removeProductRequestObject.getUserId();

        productService.deleteUser(productId, sellerId, userId);

        UserMadeDeleteBasketOperation userMadeDeleteBasketOperation = UserMadeBasketOperationFactrory
                .createUserMadeDeleteBasketOperation(removeProductRequestObject);

        kafkaService.sendUserMadeBasketOperationMessage(userMadeDeleteBasketOperation);

        return new ResponseEntity<String>("Product is deleted", HttpStatus.OK);
    }
}
