package com.microservices.ecommerce.basket.service.routes;

import com.microservices.ecommerce.basket.service.routes.request.models.AddProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.RemoveProductRequestObject;
import com.microservices.ecommerce.basket.service.routes.request.models.SetProductQuantityRequestObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/basket-product")
public class BasketProductOperations {

    @PutMapping(path = "/add-product")
    public ResponseEntity<String> addProductToBasket(@RequestBody AddProductRequestObject addProductRequestObject) {
        ResponseEntity<String> responseEntity;
        // Check Object is correct

        //Check product and seller is exist DB

            //if not add product and seller

        //Add User to Product and Seller

        //Send event
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }

    @PostMapping(path = "/set-product-quantity")
    public ResponseEntity<String> setProductQuantity(@RequestBody SetProductQuantityRequestObject setProductQuantityRequestObject) {
        ResponseEntity<String> responseEntity;
        // Check Object is correct

        //Check product and seller is exist DB

            //if not add product and seller

        //Check user is exist DB

            //if not add user

        //Update User Quantity

        //Send event
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove-product")
    public  ResponseEntity<String> removeProduct(@RequestBody RemoveProductRequestObject removeProductRequestObject) {
        ResponseEntity<String> responseEntity;
        // Check Object is correct

        //Check product, seller and user is exist DB

            //if not throw error

        //remove user from product

        //Send event
        return new ResponseEntity<String>("hello world", HttpStatus.OK);
    }
}
