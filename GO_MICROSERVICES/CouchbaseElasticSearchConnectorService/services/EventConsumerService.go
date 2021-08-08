package services

import (
	"CouchbaseElasticSearchConnectorService/config"
	"CouchbaseElasticSearchConnectorService/kafka"
	"CouchbaseElasticSearchConnectorService/models"
	"context"
)
func UserMadeAddBasket(event models.UserMadeAddBasketOperation, connection *elasticSearchConnection)  {
	//fmt.Println(event.TopicName, " ", event.ProductTitle)
	basket := getBasketByUserId(event.UserId, connection)
	basket = AddBasketOperationEventTransformToBasket(event, basket)

	createOrUpdateBasket(basket, connection)

	sendBasketUpdatedEvent(basket)
}

func UserMadeUpdateBasket(event models.UserMadeUpdateBasketOperation, connection *elasticSearchConnection)  {
	//fmt.Println(event.TopicName, " ", event.ProductTitle)
	addEvent := models.TransformToUserMadeAddBasketOperation(event)
	UserMadeAddBasket(addEvent, connection)
}

func UserMadeDeleteBasket(event models.UserMadeDeleteBasketOperation, connection *elasticSearchConnection)  {
	//fmt.Println(event.TopicName, " ", event.ProductId)
	basket := getBasketByUserId(event.UserId, connection)
	basket = DeleteBasketOperationEventTransformToBasket(event, basket)

	createOrUpdateBasket(basket, connection)

	sendBasketUpdatedEvent(basket)
}

func PromotionInBasketChanged(event models.Basket, connection *elasticSearchConnection) {
	//fmt.Println(event.UserId, " ", event.Promotions)
	createOrUpdateBasket(event, connection)
}

func PromotionIsOver(event models.PromotionIsOver, connection *elasticSearchConnection) {
	//fmt.Println(event.UserList)
	baskets := getBaskets(event, connection)
	for _,basket := range baskets {
		sendBasketUpdatedEvent(basket)
	}

}

func PromotionListChanged(event string, connection *elasticSearchConnection) {
	//fmt.Println(event)
	baskets := sendQueryAndReturnBaskets("_index", "baskets", connection)
	for _,basket := range baskets {
		sendBasketUpdatedEvent(basket)
	}
}
func getBaskets(event models.PromotionIsOver, connection *elasticSearchConnection) []models.Basket{
	var baskets []models.Basket
	for _,userId := range event.UserList {
		basket := getBasketByUserId(userId, connection)
		baskets = append(baskets, basket)
	}
	return baskets
}

func sendBasketUpdatedEvent( basket models.Basket) {
	ctx, _ := context.WithCancel(context.Background())
		kafka.Produce(config.ReadConfigVariable().TopicNames.BasketUpdated,
		config.ReadConfigVariable().KafkaConfig.Broker,
		ctx,
		basket)
}
