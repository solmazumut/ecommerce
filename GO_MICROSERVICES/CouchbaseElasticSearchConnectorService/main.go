package main

import (
	"CouchbaseElasticSearchConnectorService/elasticSearch"
	"CouchbaseElasticSearchConnectorService/services"
	"context"
	"fmt"
)

func main() {
	ctx := context.Background()
	esClient, err := elasticSearch.GetESClient()
	if err != nil {

		fmt.Println("Error initializing : ", err)
		panic("Client fail ")
	}
	elasticSearchConnection := services.NewElasticSearchConnection(ctx, esClient)

	services.ListenAllTopics(elasticSearchConnection)
}