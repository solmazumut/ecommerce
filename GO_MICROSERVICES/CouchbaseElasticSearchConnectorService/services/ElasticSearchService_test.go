package services

import (
	"CouchbaseElasticSearchConnectorService/elasticSearch"
	"CouchbaseElasticSearchConnectorService/models"
	"context"
	"fmt"
	"testing"
	"time"
)

func TestAddAndGetBasket(t *testing.T) {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	connection := NewElasticSearchConnection(ctx, esClient)
	basket := models.CreateTestBasket()
	addBasketToElasticSearch(basket, connection)
	time.Sleep(1 * time.Second)
	foundBasket := getBasketByUserId(basket.UserId, connection)
	if(foundBasket.UserId != basket.UserId) {
		fmt.Println(foundBasket.UserId, " ", basket.UserId)
		t.Error("ID's don't match")
	}
}

func TestAddSameBasketAgain(t *testing.T) {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	connection := NewElasticSearchConnection(ctx, esClient)
	basket := models.CreateTestBasket()
	addBasketToElasticSearch(basket, connection)
	addBasketToElasticSearch(basket, connection)

	hit := sendQueryAndReturnRespond("userId", basket.UserId, connection)
	thereIsOnlyOneDocumentExistInDB := 1
	if(len(hit) != thereIsOnlyOneDocumentExistInDB) {
		t.Error()
	}
}

func TestUpdateBasket(t *testing.T) {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	connection := NewElasticSearchConnection(ctx, esClient)
	basket := models.CreateTestBasket()
	addBasketToElasticSearch(basket, connection)
	time.Sleep(1 * time.Second)
	testTitle := "nameTest"
	basket.Sellers[0].Products[0].ProductTitle = testTitle
	updateBasket(basket, connection)
	time.Sleep(1 * time.Second)
	foundBasket := getBasketByUserId(basket.UserId, connection)
	foundTitle := foundBasket.Sellers[0].Products[0].ProductTitle
	if( foundTitle != testTitle) {
		fmt.Println(foundTitle, " ", testTitle)
		t.Error("Title's don't match")
	}
}

func TestDeleteBasket(t *testing.T) {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	connection := NewElasticSearchConnection(ctx, esClient)
	basket := models.CreateTestBasket()

	addBasketToElasticSearch(basket, connection)
	time.Sleep(1 * time.Second)
	deleteBasketWithId(basket.UserId, connection)
	time.Sleep(1 * time.Second)
	foundBasket := getBasketByUserId(basket.UserId, connection)

	if foundBasket.UserId != 0 &&
		len(foundBasket.Sellers) != 0 &&
		len(foundBasket.Promotions) != 0 {
		t.Error("Basket Not Deleted")
	}
}
