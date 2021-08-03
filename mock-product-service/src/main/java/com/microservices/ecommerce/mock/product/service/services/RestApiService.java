package com.microservices.ecommerce.mock.product.service.services;

import com.microservices.ecommerce.mock.product.service.models.Product;
import com.microservices.ecommerce.mock.product.service.models.ProductList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/product")
public class RestApiService {

    CreateMockData mockData = new CreateMockData();

    @GetMapping(path = "/all-products")
    public @ResponseBody ResponseEntity<ProductList>  getProducts()
    {
        ResponseEntity<ProductList> listResponseEntity;
        listResponseEntity = new ResponseEntity<ProductList>(new ProductList (mockData.getProducts()), HttpStatus.OK);
        return listResponseEntity;
    }

    @GetMapping("/get/{id}")
    public @ResponseBody ResponseEntity<Product> getById(@PathVariable int id) {
        ResponseEntity<Product> productResponseEntity;
        if(id == 1) {
            productResponseEntity = new ResponseEntity<Product>(mockData.getProduct1(), HttpStatus.OK);
        } else if(id == 2) {
            productResponseEntity = new ResponseEntity<Product>(mockData.getProduct2(), HttpStatus.OK);
        } else if(id == 3) {
            productResponseEntity = new ResponseEntity<Product>(mockData.getProduct3(), HttpStatus.OK);
        } else if(id == 4) {
            productResponseEntity = new ResponseEntity<Product>(mockData.getProduct4(), HttpStatus.OK);
        } else {
            productResponseEntity = new ResponseEntity<Product>(mockData.getProduct5(), HttpStatus.OK);
        }
        return productResponseEntity;
    }

}
