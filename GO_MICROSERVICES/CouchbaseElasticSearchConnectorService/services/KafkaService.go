package services

import (
	"CouchbaseElasticSearchConnectorService/config"
	"CouchbaseElasticSearchConnectorService/kafka"
	"context"
	"log"
	"os"
	"os/signal"
)

type kafkaService struct {
	Broker string
	GroupId string
	Service func(string, *elasticSearchConnection)
	Connection *elasticSearchConnection
}

func newKafkaService(connection *elasticSearchConnection) *kafkaService {
	return &kafkaService{
		Broker: config.ReadConfigVariable().KafkaConfig.Broker,
		GroupId: config.ReadConfigVariable().KafkaConfig.GroupId,
		Service: serilizeStringAndAct,
		Connection: connection,
	}
}


func ListenAllTopics(elasticSearchConnection *elasticSearchConnection) {

	kafkaServiceStruct := newKafkaService(elasticSearchConnection)


	addEventTopic := config.ReadConfigVariable().TopicNames.UserMadeAddBasketOperation
	receivedAddEvent := make(chan string)
	go listenAndAct(kafkaServiceStruct, addEventTopic, receivedAddEvent)

	updateEventTopic := config.ReadConfigVariable().TopicNames.UserMadeUpdateBasketOperation
	receivedUpdateEvent := make(chan string)
	go listenAndAct(kafkaServiceStruct, updateEventTopic, receivedUpdateEvent)

	deleteEventTopic := config.ReadConfigVariable().TopicNames.UserMadeDeleteBasketOperation
	receivedDeleteEvent := make(chan string)
	go listenAndAct(kafkaServiceStruct, deleteEventTopic, receivedDeleteEvent)

	promotionInBasketChangedEventTopic := config.ReadConfigVariable().TopicNames.PromotionInBasketChanged
	receivedPromotionInBasketChangedEvent := make(chan string)
	go listenAndAct(kafkaServiceStruct, promotionInBasketChangedEventTopic,receivedPromotionInBasketChangedEvent)

	promotionIsOverEventTopic := config.ReadConfigVariable().TopicNames.PromotionIsOver
	receivedPromotionIsOverEvent := make(chan string)
	go listenAndAct(kafkaServiceStruct, promotionIsOverEventTopic, receivedPromotionIsOverEvent)

	promotionListChangedEventTopic := config.ReadConfigVariable().TopicNames.PromotionListChanged
	receivedPromotionListChangedEvent := make(chan string)
	listenAndAct(kafkaServiceStruct, promotionListChangedEventTopic, receivedPromotionListChangedEvent)

}

func listenAndAct(kafkaServiceStruct *kafkaService, topicName string, receivedEvent chan string) {

	ctx, cancel := context.WithCancel(context.Background())
	addBasketEventListener := kafka.NewEventListener(kafkaServiceStruct.Broker, topicName, ctx)

	var terminate chan os.Signal = make(chan os.Signal, 1)
	signal.Notify(terminate, os.Interrupt)

	go addBasketEventListener.StartAndListenChannel(kafkaServiceStruct.Broker, receivedEvent)

	for {
		select {
		case received := <-receivedEvent:
			kafkaServiceStruct.Service(received, kafkaServiceStruct.Connection)
		case <-terminate:
			cancel()
			<-receivedEvent
			log.Println("exiting..")
			os.Exit(1)

		}
	}
}
