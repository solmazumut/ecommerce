package kafka

import (
	"CouchbaseElasticSearchConnectorService/config"
	"context"
	"fmt"
	"github.com/segmentio/kafka-go"
	"log"
)

type eventListener struct {
	Broker        string
	Topic         string
	CancelContext context.Context
}

func NewEventListener(broker string, topic string, cancelContext context.Context) *eventListener {
	return &eventListener{
		Broker:        broker,
		Topic:         topic,
		CancelContext: cancelContext,
	}
}

func (eventListener *eventListener) StartAndListenChannel(groupId string, event chan string) {
	readerConfig := kafka.ReaderConfig{
		Brokers: []string{eventListener.Broker},
		Topic:   eventListener.Topic,
		GroupID: groupId,
	}

	reader := kafka.NewReader(readerConfig)
	messageSplitter := config.ReadConfigVariable().TopicNames.MessageSplitter
	for {
		select {
		case <-eventListener.CancelContext.Done():
			reader.Close()
			event <- "Done"
		default:

			msg, err := reader.ReadMessage(eventListener.CancelContext)
			if err != nil {
				log.Println("cannot read message " + err.Error())
			}

			event <- fmt.Sprintf("%s%s%s", string(msg.Topic),messageSplitter, string(msg.Value))
		}

	}
}
