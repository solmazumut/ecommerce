package routes

import (
	"BasketQueryService/elasticSearch"
	"BasketQueryService/models"
	"BasketQueryService/services"
	"context"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
)
type RestObject struct {

}

func getBasket(w http.ResponseWriter, r *http.Request){
	userIds, okUser := r.URL.Query()["userId"]
	isUserKeyIdExist := isKeyExit(userIds)
	if(isUserKeyIdExist) {
		if !okUser || len(userIds[0]) > 0 {
			userId := userIds[0]
			log.Println("Url Param userId is: " + string(userId))
			connection := getConnection()
			foundBasket := services.GetBasketByUserId(userId, connection)
			data := serilizeStruct(foundBasket)
			fmt.Fprintf(w, data)
		}
	}
}

func handler(w http.ResponseWriter, r *http.Request) {

	userIds, okUser := r.URL.Query()["userId"]

	productIds, okProduct := r.URL.Query()["productId"]

	sellerIds, okSeller := r.URL.Query()["sellerId"]
	isUserKeyIdExist := isKeyExit(userIds)
	isProductKeyIdExist := isKeyExit(productIds)
	isSellerKeyIdExist := isKeyExit(sellerIds)
	if(isUserKeyIdExist) {
		if !okUser || len(userIds[0]) > 0 {
			userId := userIds[0]
			connection := getConnection()
			foundBaskets := services.SendQueryAndReturnBaskets("userId", userId, connection)
			var resultArr []string
			resultArr = serilizeList(foundBaskets, resultArr)
			for _,basket := range resultArr {
				fmt.Fprintf(w, basket)
			}
		}
	} else if isProductKeyIdExist {
		if !okProduct || len(productIds[0]) > 0 {
			productId := productIds[0]
			connection := getConnection()
			foundBaskets := services.SendQueryAndReturnBaskets("Sellers.Products.productId", productId, connection)
			var resultArr []string
			resultArr = serilizeList(foundBaskets, resultArr)
			for _,basket := range resultArr {
				fmt.Fprintf(w, basket)
			}
		}
	} else if isSellerKeyIdExist {
		if !okSeller || len(sellerIds[0]) > 0 {
			sellerId := sellerIds[0]
			connection := getConnection()
			foundBaskets := services.SendQueryAndReturnBaskets("Sellers.sellerId", sellerId, connection)
			var resultArr []string
			resultArr = serilizeList(foundBaskets, resultArr)
			for _,basket := range resultArr {
				fmt.Fprintf(w, basket)
			}
		}
	}

}

func serilizeList(foundBaskets []models.Basket, resultArr []string) []string{
	for _, basket := range foundBaskets {
		data := serilizeStruct(basket)
		resultArr = append(resultArr, data)
	}
	return resultArr
}

func isKeyExit(arr  []string) bool{
	result := true
	if(len(arr) < 1) {
		result = false
	}
	return result
}

func handleRequests() {
	http.HandleFunc("/", handler)
	http.HandleFunc("/basket", getBasket)
	http.ListenAndServe(":8083", nil)
}

func (rest *RestObject) GetRequest() {
	handleRequests()
}

func getConnection() *services.ElasticSearchConnection {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	elasticSearchConnection := services.NewElasticSearchConnection(ctx, esClient)
	return elasticSearchConnection

}

func serilizeStruct(basket models.Basket) string{
	dataJSON, err := json.Marshal(basket)
	if err != nil {
		log.Fatal("marshaling error: ", err)
	}
	js := string(dataJSON)
	return js
}
