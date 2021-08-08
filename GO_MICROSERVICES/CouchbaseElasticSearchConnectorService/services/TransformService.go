package services

import "CouchbaseElasticSearchConnectorService/models"

func AddBasketOperationEventTransformToBasket(event models.UserMadeAddBasketOperation, basket models.Basket) models.Basket {
	basket.UserId = event.UserId
	newBasket := basket
	newSellerId := event.SellerId
	newProductId := event.ProductId
	newProductTitle := event.ProductTitle
	newProductImageUrl := event.ProductImageUrl
	newQuantity := event.Quantity
	newPrice := event.Price

	notFound := -1
	sellerIndex := getSellerIndex(basket.Sellers, newSellerId)
	if sellerIndex != notFound {
		productIndex := getProductIndex(basket.Sellers[sellerIndex].Products, newProductId)
		if productIndex != notFound {
			newBasket.Sellers[sellerIndex].Products = updateProducts(basket.Sellers[sellerIndex].Products, productIndex,
				newProductId, newProductTitle, newProductImageUrl, newQuantity, newPrice)
		} else {
			newBasket.Sellers = addProductToSeller(basket.Sellers, sellerIndex,
				newProductId, newProductTitle, newProductImageUrl, newQuantity, newPrice)
		}
	} else {
		newBasket = addSellerAndProductToBasket(basket, newSellerId,
			newProductId, newProductTitle, newProductImageUrl, newQuantity, newPrice)
	}
	return newBasket
}

func DeleteBasketOperationEventTransformToBasket(event models.UserMadeDeleteBasketOperation, basket models.Basket) models.Basket {
	basket.UserId = event.UserId
	newBasket := basket
	newSellerId := event.SellerId
	newProductId := event.ProductId

	notFound := -1
	sellerIndex := getSellerIndex(basket.Sellers, newSellerId)
	if sellerIndex != notFound {
		productIndex := getProductIndex(basket.Sellers[sellerIndex].Products, newProductId)
		if productIndex != notFound {
			products := newBasket.Sellers[sellerIndex].Products
			products = append(products[:productIndex], products[productIndex+1:]...)
			newBasket.Sellers[sellerIndex].Products = products
		} 
	}
	return newBasket
}


func addSellerAndProductToBasket(basket models.Basket, productId int64, sellerId int64,
	title string, url string, quantity int, price float32) models.Basket {

	product := models.NewProduct(productId, title, url, price, quantity)
	var products []models.Product
	products = append(products, product)
	seller := models.NewSeller(sellerId, products)
	sellerList := basket.Sellers
	sellerList = append(sellerList, seller)
	basket.Sellers = sellerList
	return basket
}

func addProductToSeller(sellers []models.Seller, index int, id int64, title string,
	url string, quantity int, price float32) []models.Seller {
	product := models.NewProduct(id, title, url, price, quantity)
	products := sellers[index].Products
	sellers[index].Products = append(products, product)
	return sellers
}

func updateProducts(products []models.Product, index int, id int64, title string,
	url string, quantity int, price float32) []models.Product {
	product := models.NewProduct(id, title, url, price, quantity)
	products[index] = product
	return products
}


func getSellerIndex(sellers []models.Seller, sellerId int64) int  {
	sellerIndex := -1
	numberOfSellers := len(sellers)
	for seller := 0; seller < numberOfSellers; seller++ {
		if sellers[seller].SellerId == sellerId {
			sellerIndex = seller
			break
		}
	}
	return sellerIndex
}

func getProductIndex(products []models.Product, productId int64) int  {
	productIndex := -1
	numberOfSellers := len(products)
	for product := 0; product < numberOfSellers; product++ {
		if products[product].ProductId == productId {
			productIndex = product
			break
		}
	}
	return productIndex
}