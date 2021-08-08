package services

import (
	"CouchbaseElasticSearchConnectorService/config"
	"CouchbaseElasticSearchConnectorService/models"
	"encoding/json"
	"fmt"
	"strings"
)

func serilizeStringAndAct(event string, connection *elasticSearchConnection) {

	eventArray := splitStringToArray(event)
	topicName := eventArray[0]
	jsonString := addApostropheHeadAndTailToString(eventArray[1])

	addBasketTopic := config.ReadConfigVariable().TopicNames.UserMadeAddBasketOperation
	updateBasketTopic := config.ReadConfigVariable().TopicNames.UserMadeUpdateBasketOperation
	deleteBasketTopic := config.ReadConfigVariable().TopicNames.UserMadeDeleteBasketOperation
	promotionInBasketChangedTopic := config.ReadConfigVariable().TopicNames.PromotionInBasketChanged
	promotionIsOverTopic := config.ReadConfigVariable().TopicNames.PromotionIsOver
	promotionListChangedTopic := config.ReadConfigVariable().TopicNames.PromotionListChanged

	switch topicName {
		case addBasketTopic:
			var userMadeAddBasketOperation models.UserMadeAddBasketOperation
			json.Unmarshal([]byte(jsonString), &userMadeAddBasketOperation)
			UserMadeAddBasket(userMadeAddBasketOperation, connection)

		case updateBasketTopic:
			var userMadeUpdateBasketOperation models.UserMadeUpdateBasketOperation
			json.Unmarshal([]byte(jsonString), &userMadeUpdateBasketOperation)
			UserMadeUpdateBasket(userMadeUpdateBasketOperation, connection)

		case deleteBasketTopic:
			var userMadeDeleteBasketOperation models.UserMadeDeleteBasketOperation
			//json.Unmarshal([]byte(`${jsonString}`), &userMadeDeleteBasketOperation)
			json.Unmarshal([]byte(jsonString), &userMadeDeleteBasketOperation)
			UserMadeDeleteBasket(userMadeDeleteBasketOperation, connection)

		case promotionInBasketChangedTopic:
			var basket models.Basket
			//json.Unmarshal([]byte(`${jsonString}`), &userMadeDeleteBasketOperation)
			json.Unmarshal([]byte(jsonString), &basket)
			PromotionInBasketChanged(basket, connection)

		case promotionIsOverTopic:
			var userList models.PromotionIsOver
			//json.Unmarshal([]byte(`${jsonString}`), &userMadeDeleteBasketOperation)
			json.Unmarshal([]byte(jsonString), &userList)
			PromotionIsOver(userList, connection)

		case promotionListChangedTopic:
			var message string
			//json.Unmarshal([]byte(`${jsonString}`), &userMadeDeleteBasketOperation)
			json.Unmarshal([]byte(jsonString), &message)
			PromotionListChanged(message, connection)

	}

}

func splitStringToArray(event string) []string {
	messageSplitter := config.ReadConfigVariable().TopicNames.MessageSplitter
	eventArray := strings.Split(event, messageSplitter)
	return eventArray
}

func addApostropheHeadAndTailToString(jsonString string) string {
	modifiedJsonString := fmt.Sprintf(`%s`,jsonString)
	return modifiedJsonString
}