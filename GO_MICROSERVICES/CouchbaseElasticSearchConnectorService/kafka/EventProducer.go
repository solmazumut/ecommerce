package kafka

import (
	"CouchbaseElasticSearchConnectorService/models"
	"context"
	"encoding/json"
	"github.com/segmentio/kafka-go"
	"log"
	"strconv"
)

type eventProducer struct {
	Broker        string
	Topic         string
	CancelContext context.Context
}

func NewEventProducer(broker string, topic string, cancelContext context.Context) *eventProducer {
	return &eventProducer{
		Broker:        broker,
		Topic:         topic,
		CancelContext: cancelContext,
	}
}

func Produce(topic string, broker string, ctx context.Context, basket models.Basket) {

	w := kafka.NewWriter(kafka.WriterConfig{
		Brokers: []string{broker},
	})

	// each kafka message has a key and value. The key is used
	// to decide which partition (and consequently, which broker)
	// the message gets published on
	message := serilizeStruct(basket)

	err := w.WriteMessages(ctx, kafka.Message{
		Topic: topic,
		Key: []byte(strconv.Itoa(1)),
		// create an arbitrary message payload for the value
		Value: []byte(message),
	})
	if err != nil {
		panic("could not write message " + err.Error())
	}
}

func serilizeStruct(basket models.Basket) string{
	dataJSON, err := json.Marshal(basket)
	if err != nil {
		log.Fatal("marshaling error: ", err)
	}
	js := string(dataJSON)
	return js
}


