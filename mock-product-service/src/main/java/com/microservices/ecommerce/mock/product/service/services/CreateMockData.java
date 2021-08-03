package com.microservices.ecommerce.mock.product.service.services;

import com.microservices.ecommerce.mock.product.service.models.Product;
import com.microservices.ecommerce.mock.product.service.models.Seller;

import java.util.List;

public class CreateMockData {

    private Seller sellerA = new Seller(1, "SellerA", 0, 0, 5f, 5);
    private Seller sellerB = new Seller(2, "sellerB", 0, 0, 4.9f, 5);
    private Seller sellerC = new Seller(3, "sellerC", 0, 0, 3f, 5);
    private Seller sellerD = new Seller(4, "sellerD", 0, 0, 4.1f, 5);
    private Seller sellerE = new Seller(5, "sellerE", 0, 0, 4.5f, 5);


    private Product product1 = new Product(
            1,
            "phone",
            List.of(sellerA.setPriceAndStock(500, 10),
                    sellerB.setPriceAndStock(450, 4),
                    sellerC.setPriceAndStock(600, 5)),
            List.of("one size")
    );
    private Product product2 = new Product(
            2,
            "computer",
            List.of(sellerA.setPriceAndStock(5000, 1),
                    sellerB.setPriceAndStock(10000, 10),
                    sellerD.setPriceAndStock(7500, 4),
                    sellerE.setPriceAndStock(6500, 9)),
            List.of("one size")
    );
    private Product product3 = new Product(
            3,
            "headphone",
            List.of(sellerB.setPriceAndStock(80, 4),
                    sellerC.setPriceAndStock(39, 2),
                    sellerD.setPriceAndStock(50, 5),
                    sellerD.setPriceAndStock(63, 3)),
            List.of("black", "white")
    );
    private Product product4 = new Product(
            4,
            "t-shirt",
            List.of(sellerA.setPriceAndStock(70, 10),
                    sellerD.setPriceAndStock(79, 4),
                    sellerE.setPriceAndStock(80, 5)),
            List.of("S", "M", "L", "XL")
    );
    private Product product5 = new Product(
            5,
            "shoe",
            List.of(sellerA.setPriceAndStock(600, 10),
                    sellerB.setPriceAndStock(750, 4),
                    sellerC.setPriceAndStock(700, 5),
                    sellerD.setPriceAndStock(700, 4)),
            List.of("38", "39", "40", "41", "42") 
    );

    public CreateMockData() {
    }

    List<Product> getProducts() {
        return List.of(product1, product2, product3, product4, product5);
    }

    public Seller getSellerA() {
        return sellerA;
    }

    public void setSellerA(Seller sellerA) {
        this.sellerA = sellerA;
    }

    public Seller getSellerB() {
        return sellerB;
    }

    public void setSellerB(Seller sellerB) {
        this.sellerB = sellerB;
    }

    public Seller getSellerC() {
        return sellerC;
    }

    public void setSellerC(Seller sellerC) {
        this.sellerC = sellerC;
    }

    public Seller getSellerD() {
        return sellerD;
    }

    public void setSellerD(Seller sellerD) {
        this.sellerD = sellerD;
    }

    public Seller getSellerE() {
        return sellerE;
    }

    public void setSellerE(Seller sellerE) {
        this.sellerE = sellerE;
    }

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    public Product getProduct2() {
        return product2;
    }

    public void setProduct2(Product product2) {
        this.product2 = product2;
    }

    public Product getProduct3() {
        return product3;
    }

    public void setProduct3(Product product3) {
        this.product3 = product3;
    }

    public Product getProduct4() {
        return product4;
    }

    public void setProduct4(Product product4) {
        this.product4 = product4;
    }

    public Product getProduct5() {
        return product5;
    }

    public void setProduct5(Product product5) {
        this.product5 = product5;
    }
}
