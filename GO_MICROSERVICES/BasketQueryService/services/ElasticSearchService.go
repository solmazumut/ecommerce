package services

import (
	"BasketQueryService/models"
	"context"
	"encoding/json"
	"fmt"
	"gopkg.in/olivere/elastic.v7"
)

type ElasticSearchConnection struct {
	BackgroundContext   context.Context
	ElasticSearchClient *elastic.Client
}



func NewElasticSearchConnection(ctx context.Context, esClient *elastic.Client) *ElasticSearchConnection {
	return &ElasticSearchConnection{
		BackgroundContext:   ctx,
		ElasticSearchClient: esClient,
	}
}



func sendQueryAndReturnRespond(query string, queryData string,
	connection *ElasticSearchConnection)[]*elastic.SearchHit {
	searchSource := elastic.NewSearchSource()
	searchSource.Query(elastic.NewMatchQuery(query, queryData))
	queryStr, err1 := searchSource.Source()
	queryJs, err2 := json.Marshal(queryStr)
	if err1 != nil || err2 != nil {
		fmt.Println("[esClient][GetResponse]err during query marshal=", err1, err2)
	}
	fmt.Println("[esClient]Final ESQuery=\n", string(queryJs))
	searchService := connection.
		ElasticSearchClient.Search().Index("baskets").SearchSource(searchSource)

	searchResult, err3 := searchService.Do(connection.BackgroundContext)
	if err3 != nil {
		fmt.Println("[BasketsES][GetPIds]Error=", err3)
	}
	var hit = searchResult.Hits.Hits
	return hit
}


func SendQueryAndReturnBaskets(query string, queryData string,
	connection *ElasticSearchConnection) []models.Basket {

	var baskets []models.Basket
	hit := sendQueryAndReturnRespond(query, queryData, connection)
	foundSize := len(hit)
	notFound := 0
	if(foundSize > notFound) {
		for _, basketInRespond := range hit{
			var basket models.Basket
			err := json.Unmarshal(basketInRespond.Source, &basket)
			if err != nil {
				fmt.Println("[Getting Baskets][Unmarshal] Err=", err)
			}
			baskets = append(baskets, basket)
		}
	}
	return baskets
}


func GetBasketByUserId(userId string, connection *ElasticSearchConnection) models.Basket {
	var basket models.Basket
	searchSource := elastic.NewSearchSource()
	searchSource.Query(elastic.NewMatchQuery("userId", userId))
	queryStr, err1 := searchSource.Source()
	queryJs, err2 := json.Marshal(queryStr)
	if err1 != nil || err2 != nil {
		fmt.Println("[esClient][GetResponse]err during query marshal=", err1, err2)
	}
	fmt.Println("[esClient]Final ESQuery=\n", string(queryJs))
	searchService := connection.
		ElasticSearchClient.Search().Index("baskets").SearchSource(searchSource)

	searchResult, err3 := searchService.Do(connection.BackgroundContext)
	if err3 != nil {
		fmt.Println("[BasketsES][GetPIds]Error=", err3)
	}
	var hit = searchResult.Hits.Hits
	foundSize := len(hit)
	notFound := 0
	if(foundSize > notFound) {
		basketJson := hit[0].Source
		err4 := json.Unmarshal(basketJson, &basket)
		if err4 != nil {
			fmt.Println("[Getting Baskets][Unmarshal] Err=", err4)
		}
	}

	return basket
}
